package com.zsmartsystems.zigbee.autocode;

import com.zsmartsystems.zigbee.autocode.xml.ZigBeeXmlCluster;
import com.zsmartsystems.zigbee.autocode.xml.ZigBeeXmlConstant;
import com.zsmartsystems.zigbee.autocode.xml.ZigBeeXmlGlobal;
import com.zsmartsystems.zigbee.autocode.xml.ZigBeeXmlParser;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Execute;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.IOUtil;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Mojo(name="generate-zcl", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
@Execute(phase = LifecyclePhase.GENERATE_SOURCES)
public class AutoCodeMojo extends AbstractMojo {
    @Parameter(readonly = true, required = true)
    private File zclInput;

    @Parameter(readonly = true, required = true)
    private File zdoInput;

    @Parameter(readonly = true, required = true)
    private File constantsInput;

    @Parameter(readonly = true, required = true, defaultValue = "${project.build.directory}/generated-zcl")
    private String outputDirectory;

    @Parameter(readonly = true, required = true, defaultValue = "${project.parent.basedir}/src/etc/header.txt")
    private File licenseHeaderTemplateFile;

    @Parameter(readonly = true, required = true, defaultValue = "${license.year}")
    private String licenseYear;

    @Parameter(readonly = true, defaultValue = "${project}")
    private MavenProject project;

    @Override
    public void execute() throws MojoFailureException {
        project.addCompileSourceRoot(outputDirectory);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String generatedDate = dateFormat.format(new Date());
        String license;
        try {
            license = license();
        } catch (IOException e) {
            throw new MojoFailureException("Could not read license", e);
        }

        ZigBeeXmlParser zclParser = new ZigBeeXmlParser();
        for (File f: listContents(zclInput)) {
            zclParser.addFile(f.getAbsolutePath());
        }

        List<ZigBeeXmlCluster> zclClusters = zclParser.parseClusterConfiguration();

        ZigBeeXmlParser zdoParser = new ZigBeeXmlParser();
        for (File f: listContents(zdoInput)) {
            zdoParser.addFile(f.getAbsolutePath());
        }

        List<ZigBeeXmlCluster> zdoClusters = zdoParser.parseClusterConfiguration();

        // Process all enums, bitmaps and structures first so we have a consolidated list.
        // We use this later when generating the imports in the cluster and command classes.
        List<ZigBeeXmlCluster> allClusters = new ArrayList<>();
        allClusters.addAll(zclClusters);
        allClusters.addAll(zdoClusters);
        ZigBeeZclDependencyGenerator typeGenerator = new ZigBeeZclDependencyGenerator(outputDirectory, license, allClusters);
        Map<String, String> zclTypes = typeGenerator.getDependencyMap();
        Set<String> dataTypes = typeGenerator.getZclTypeMap();

        new ZigBeeZclClusterGenerator(outputDirectory, license, zclClusters, generatedDate, zclTypes);
        new ZigBeeZclCommandGenerator(outputDirectory, license, zclClusters, generatedDate, zclTypes);
        new ZigBeeZclConstantGenerator(outputDirectory, license, zclClusters, generatedDate, zclTypes);
        new ZigBeeZclStructureGenerator(outputDirectory, license, zclClusters, generatedDate, zclTypes);
        new ZigBeeZclClusterTypeGenerator(outputDirectory, license, zclClusters, generatedDate, zclTypes);
        new ZigBeeZclCommandTypeGenerator(outputDirectory, license, zclClusters, generatedDate, zclTypes);
        new ZigBeeZclDataTypeGenerator(outputDirectory, license, dataTypes, generatedDate);

        new ZigBeeZclCommandGenerator(outputDirectory, license, zdoClusters, generatedDate, zclTypes);

        zclParser = new ZigBeeXmlParser();
        for (File f: listContents(constantsInput)) {
            zclParser.addFile(f.getAbsolutePath());
        }
        ZigBeeXmlGlobal globals = zclParser.parseGlobalConfiguration();

        for (ZigBeeXmlConstant constant : globals.constants) {
            new ZigBeeZclConstantGenerator(outputDirectory, license, constant, generatedDate);
        }

        // FIXME how to handle?
        // new ZigBeeZclReadmeGenerator(outputDirectory, license, zclClusters);
    }

    private Iterable<File> listContents(File input) {
        if (input.isFile()) {
            return Collections.singleton(input);
        }
        if (input.isDirectory()) {
            return Arrays.asList(input.listFiles());
        }
        throw new IllegalArgumentException();
    }

    private String license() throws IOException {
        try (InputStream input = new FileInputStream(licenseHeaderTemplateFile)) {
            String license = IOUtil.toString(input);
            return license.replace("\\$\\{year\\}", licenseYear);
        }
    }
}
