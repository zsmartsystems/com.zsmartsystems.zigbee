package com.zsmartsystems.zigbee.autocode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

import com.zsmartsystems.zigbee.autocode.zcl.Attribute;
import com.zsmartsystems.zigbee.autocode.zcl.Cluster;
import com.zsmartsystems.zigbee.autocode.zcl.Command;
import com.zsmartsystems.zigbee.autocode.zcl.Context;
import com.zsmartsystems.zigbee.autocode.zcl.DataType;
import com.zsmartsystems.zigbee.autocode.zcl.Field;
import com.zsmartsystems.zigbee.autocode.zcl.Profile;
import com.zsmartsystems.zigbee.autocode.zcl.ZclDataType;
import com.zsmartsystems.zigbee.autocode.zcl.ZclDataType.DataTypeMap;

/**
 * Code generator for generating ZigBee cluster library command protocol.
 *
 * @author Tommi S.E. Laukkanen
 * @author Chris Jackson
 */
public class ZclProtocolCodeGenerator {
    // The following are offsets to the root package
    static String packageZcl = ".zcl";
    static String packageZclField = packageZcl + ".field";
    static String packageZclCluster = packageZcl + ".clusters";
    static String packageZclProtocol = packageZcl + ".protocol";
    static String packageZclProtocolCommand = packageZclCluster;

    static String packageZdp = ".zdo";
    static String packageZdpCommand = packageZdp + ".command";

    /**
     * The main method for running the code generator.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(final String[] args) {
        final String definitionFilePathZcl;
        final String definitionFilePathZdp;
        // if (args.length != 0) {
        // definitionFilePathZcl = args[0];
        // } else {
        definitionFilePathZcl = "./src/main/resources/zcl_definition.md";
        definitionFilePathZdp = "./src/main/resources/zdp_definition.md";
        // }

        final String sourceRootPath;
        if (args.length != 0) {
            sourceRootPath = args[0];
        } else {
            // sourceRootPath = "./src/main/blah/";
            sourceRootPath = "../com.zsmartsystems.zigbee/src/main/java/";
        }

        final File sourceRootFile = new File(sourceRootPath);
        if (!sourceRootFile.exists()) {
            System.out.println("Source root path does not exist: " + sourceRootFile);
            return;
        }
        if (!sourceRootFile.isDirectory()) {
            System.out.println("Source root path is not directory: " + sourceRootFile);
            return;
        }

        final String packageRoot;
        if (args.length != 0) {
            packageRoot = args[0];
        } else {
            packageRoot = "com.zsmartsystems.zigbee";
        }

        final File definitionFileZcl = new File(definitionFilePathZcl);
        if (!definitionFileZcl.exists()) {
            System.out.println("Definition file does not exist: " + definitionFilePathZcl);
        } else {
            final Context contextZcl = new Context();
            try {
                contextZcl.lines = new ArrayList<String>(FileUtils.readLines(definitionFileZcl, "UTF-8"));
                generateZclCode(contextZcl, sourceRootFile, packageRoot);
            } catch (final IOException e) {
                System.out.println(
                        "Reading lines from Zcl definition file failed: " + definitionFileZcl.getAbsolutePath());
                e.printStackTrace();
            }
        }

        final File definitionFileZdp = new File(definitionFilePathZdp);
        if (!definitionFileZdp.exists()) {
            System.out.println("Definition file does not exist: " + definitionFilePathZdp);
        } else {
            final Context contextZdp = new Context();
            try {
                contextZdp.lines = new ArrayList<String>(FileUtils.readLines(definitionFileZdp, "UTF-8"));
                // generateZdpCode(contextZdp, sourceRootFile, packageRoot);
            } catch (final IOException e) {
                System.out.println(
                        "Reading lines from Zdp definition file failed: " + definitionFileZdp.getAbsolutePath());
                e.printStackTrace();
                return;
            }
        }
    }

    public static void generateZclCode(final Context context, final File sourceRootPath, final String packageRoot) {
        ZclProtocolDefinitionParser.parseProfiles(context);

        final String packagePath = getPackagePath(sourceRootPath, packageRoot);
        final File packageFile = getPackageFile(packagePath);

        try {
            generateZclDataTypeEnumeration(context, packageRoot, packageFile);
        } catch (final IOException e) {
            System.out.println("Failed to generate data types enumeration.");
            e.printStackTrace();
            return;
        }

        try {
            generateZclProfileTypeEnumeration(context, packageRoot, packageFile);
        } catch (final IOException e) {
            System.out.println("Failed to generate profile enumeration.");
            e.printStackTrace();
            return;
        }

        try {
            generateZclClusterTypeEnumeration(context, packageRoot, packageFile);
        } catch (final IOException e) {
            System.out.println("Failed to generate cluster enumeration.");
            e.printStackTrace();
            return;
        }

        // try {
        // generateZclCommandTypeEnumerationXXXXX(context, packageRoot, packageFile);
        // generateZclCommandTypeEnumeration(context, packageRoot, packageFile);
        // } catch (final IOException e) {
        // System.out.println("Failed to generate command enumeration.");
        // e.printStackTrace();
        // return;
        // }

        // try {
        // generateZclAttributeTypeEnumeration(context, packageRoot, packageFile);
        // } catch (final IOException e) {
        // System.out.println("Failed to generate attribute enumeration.");
        // e.printStackTrace();
        // return;
        // }

        // try {
        // generateZclFieldTypeEnumeration(context, packageRoot, packageFile);
        // } catch (final IOException e) {
        // System.out.println("Failed to generate field enumeration.");
        // e.printStackTrace();
        // return;
        // }

        try {
            generateZclCommandClasses(context, packageRoot, sourceRootPath);
        } catch (final IOException e) {
            System.out.println("Failed to generate profile message classes.");
            e.printStackTrace();
            return;
        }

        try {
            generateZclClusterClasses(context, packageRoot, sourceRootPath);
        } catch (final IOException e) {
            System.out.println("Failed to generate cluster classes.");
            e.printStackTrace();
            return;
        }
    }

    public static void generateZdpCode(final Context context, final File sourceRootPath, final String packageRoot) {
        ZclProtocolDefinitionParser.parseProfiles(context);

        try {
            generateZdpCommandClasses(context, packageRoot, sourceRootPath);
        } catch (final IOException e) {
            System.out.println("Failed to generate profile message classes.");
            e.printStackTrace();
            return;
        }
    }

    private static void outputClassJavaDoc(final PrintWriter out) {
        out.println("/**");
        out.println(" * Code is auto-generated. Modifications may be overwritten!");
        out.println(" */");
    }

    private static File getPackageFile(String packagePath) {
        final File packageFile = new File(packagePath);
        if (!packageFile.exists()) {
            packageFile.mkdirs();
        }
        return packageFile;
    }

    private static String getPackagePath(File sourceRootPath, String packageRoot) {
        return sourceRootPath.getAbsolutePath() + File.separator + packageRoot.replace(".", File.separator);
    }

    private static void generateZclDataTypeEnumeration(Context context, final String packageRootPrefix,
            File sourceRootPath) throws IOException {
        final String className = "ZclDataType";

        final String packageRoot = packageRootPrefix + packageZclProtocol;
        final String packagePath = getPackagePath(sourceRootPath, packageZclProtocol);
        final File packageFile = getPackageFile(packagePath);

        final PrintWriter out = getClassOut(packageFile, className);

        out.println("package " + packageRoot + ";");

        out.println();
        out.println("import java.util.Calendar;");
        out.println("import java.util.HashMap;");
        out.println("import java.util.Map;");
        out.println("import " + packageRootPrefix + packageZclField + ".*;");
        out.println();
        outputClassJavaDoc(out);
        out.println("public enum " + className + " {");

        final LinkedList<DataType> dataTypes = new LinkedList<DataType>(context.dataTypes.values());
        for (final DataType dataType : dataTypes) {
            DataTypeMap zclDataType = ZclDataType.getDataTypeMapping().get(dataType.dataTypeType);
            final String dataTypeClass;
            if (dataType.dataTypeClass.contains("<")) {
                dataTypeClass = dataType.dataTypeClass.substring(dataType.dataTypeClass.indexOf("<") + 1,
                        dataType.dataTypeClass.indexOf(">"));
            } else {
                dataTypeClass = dataType.dataTypeClass;
            }
            out.print("    " + dataType.dataTypeType + "(\"" + dataType.dataTypeName + "\"," + dataTypeClass + ".class"
                    + ", " + String.format("0x%02X", zclDataType.id) + ", " + zclDataType.analogue + ")");
            out.println(dataTypes.getLast().equals(dataType) ? ';' : ',');
        }

        out.println();
        out.println("    private final String label;");
        out.println("    private final Class<?> dataClass;");
        out.println("    private final int id;");
        out.println("    private final boolean analogue;");
        out.println("    private static Map<Integer, " + className + "> codeTypeMapping;");
        out.println();
        out.println("    " + className
                + "(final String label, final Class<?> dataClass, final int id, final boolean analogue) {");
        out.println("        this.label = label;");
        out.println("        this.dataClass = dataClass;");
        out.println("        this.id = id;");
        out.println("        this.analogue = analogue;");
        out.println("    }");
        out.println();

        out.println("    private static void initMapping() {");
        out.println("        codeTypeMapping = new HashMap<Integer, " + className + ">();");
        out.println("        for (" + className + " s : values()) {");
        out.println("            codeTypeMapping.put(s.id, s);");
        out.println("        }");
        out.println("    }");

        out.println("    public static " + className + " getType(int id) {");
        out.println("        if (codeTypeMapping == null) {");
        out.println("            initMapping();");
        out.println("        }");

        out.println("        return codeTypeMapping.get(id);");
        out.println("    }");

        out.println();
        out.println("    public String getLabel() {");
        out.println("        return label;");
        out.println("    }");
        out.println();
        out.println("    public Class<?> getDataClass() {");
        out.println("        return dataClass;");
        out.println("    }");
        out.println();
        out.println("    public int getId() {");
        out.println("        return id;");
        out.println("    }");
        out.println();
        out.println("    public boolean isAnalog() {");
        out.println("        return analogue;");
        out.println("    }");
        out.println("}");

        out.flush();
        out.close();
    }

    private static void generateZclProfileTypeEnumeration(Context context, String packageRootPrefix,
            File sourceRootPath) throws IOException {
        final String className = "ZclProfileType";

        final String packageRoot = packageRootPrefix + packageZclProtocol;
        final String packagePath = getPackagePath(sourceRootPath, packageZclProtocol);
        final File packageFile = getPackageFile(packagePath);

        final PrintWriter out = getClassOut(packageFile, className);

        out.println("package " + packageRoot + ";");
        out.println();
        outputClassJavaDoc(out);
        out.println("public enum " + className + " {");

        final LinkedList<Profile> profiles = new LinkedList<Profile>(context.profiles.values());
        for (final Profile profile : profiles) {
            out.print("    " + profile.profileType + "(" + profile.profileId + ", \"" + profile.profileName + "\")");
            out.println(profiles.getLast().equals(profile) ? ';' : ',');
        }

        out.println();
        out.println("    private final int id;");
        out.println("    private final String label;");
        out.println();
        out.println("    " + className + "(final int id, final String label) {");
        out.println("        this.id = id;");
        out.println("        this.label = label;");
        out.println("    }");
        out.println();
        out.println("    public int getId() { return id; }");
        out.println("    public String getLabel() { return label; }");
        out.println("    public String toString() { return label; }");
        out.println();
        out.println("}");

        out.flush();
        out.close();
    }

    private static void generateZclClusterTypeEnumeration(Context context, String packageRootPrefix,
            File sourceRootPath) throws IOException {
        final String className = "ZclClusterType";

        final String packageRoot = packageRootPrefix + packageZclProtocol;
        final String packagePath = getPackagePath(sourceRootPath, packageZclProtocol);
        final File packageFile = getPackageFile(packagePath);

        final PrintWriter out = getClassOut(packageFile, className);

        out.println("package " + packageRoot + ";");
        out.println();
        out.println("import " + packageRootPrefix + packageZcl + ".ZclCluster;");
        out.println("import " + packageRootPrefix + packageZclCluster + ".*;");
        out.println();
        out.println("import java.util.HashMap;");
        out.println("import java.util.Map;");

        out.println();
        outputClassJavaDoc(out);
        out.println("public enum " + className + " {");

        final LinkedList<Profile> profiles = new LinkedList<Profile>(context.profiles.values());
        for (final Profile profile : profiles) {
            final LinkedList<Cluster> clusters = new LinkedList<Cluster>(profile.clusters.values());
            for (final Cluster cluster : clusters) {
                out.print("    " + cluster.clusterType + "(" + cluster.clusterId + ", ZclProfileType."
                        + profile.profileType + ", Zcl" + cluster.nameUpperCamelCase + "Cluster.class, \""
                        + cluster.clusterName + "\")");
                out.println(clusters.getLast().equals(cluster) ? ';' : ',');
            }
        }

        out.println();
        out.println(
                "    private static final Map<Integer, ZclClusterType> idValueMap = new HashMap<Integer, ZclClusterType>();");
        out.println();
        out.println("    private final int id;");
        out.println("    private final ZclProfileType profileType;");
        out.println("    private final String label;");
        out.println("    private final Class<? extends ZclCluster> clusterClass;");
        out.println();
        out.println("    " + className
                + "(final int id, final ZclProfileType profileType, final Class<? extends ZclCluster>clusterClass, final String label) {");
        out.println("        this.id = id;");
        out.println("        this.profileType = profileType;");
        out.println("        this.clusterClass = clusterClass;");
        out.println("        this.label = label;");
        out.println("    }");
        out.println();
        out.println("    static {");
        out.println("        for (final ZclClusterType value : values()) {");
        out.println("            idValueMap.put(value.id, value);");
        out.println("        }");
        out.println("    }");
        out.println();
        out.println("    public int getId() {");
        out.println("        return id;");
        out.println("    }");
        out.println();
        out.println("    public ZclProfileType getProfileType() {");
        out.println("        return profileType;");
        out.println("    }");
        out.println();
        out.println("    public String getLabel() {");
        out.println("        return label;");
        out.println("    }");
        out.println();
        // out.println(" public String toString() {");
        // out.println(" return label;");
        // out.println(" }");
        // out.println();
        out.println("    public Class<? extends ZclCluster> getClusterClass() {");
        out.println("        return clusterClass;");
        out.println("    }");
        out.println();
        out.println("    public static ZclClusterType getValueById(final int id) {");
        out.println("        return idValueMap.get(id);");
        out.println("    }");
        out.println();
        out.println("}");

        out.flush();
        out.close();
    }

    private static void generateZclCommandTypeEnumerationXXXXX(Context context, String packageRootPrefix,
            File sourceRootPath) throws IOException {

        final String className = "ZclCommandTypeXXX";

        final String packageRoot = packageRootPrefix + packageZclProtocol;
        final String packagePath = getPackagePath(sourceRootPath, packageZclProtocol);
        final File packageFile = getPackageFile(packagePath);

        final PrintWriter out = getClassOut(packageFile, className);

        out.println("package " + packageRoot + ";");
        out.println();
        outputClassJavaDoc(out);
        out.println("public enum " + className + " {");

        final LinkedList<String> valueRows = new LinkedList<String>();
        final LinkedList<Profile> profiles = new LinkedList<Profile>(context.profiles.values());
        for (final Profile profile : profiles) {
            final LinkedList<Cluster> clusters = new LinkedList<Cluster>(profile.clusters.values());
            for (final Cluster cluster : clusters) {
                {
                    final LinkedList<Command> commands = new LinkedList<Command>(cluster.received.values());
                    for (final Command command : commands) {
                        final boolean generic = cluster.clusterId == 65535;
                        valueRows.add("    " + command.commandType + "(" + command.commandId + ", ZclClusterType."
                                + cluster.clusterType + ", \"" + command.commandLabel + "\", true, " + generic + ")");
                    }
                }
                {
                    final LinkedList<Command> commands = new LinkedList<Command>(cluster.generated.values());
                    for (final Command command : commands) {
                        final boolean generic = cluster.clusterId == 65535;
                        valueRows.add("    " + command.commandType + "(" + command.commandId + ", ZclClusterType."
                                + cluster.clusterType + ", \"" + command.commandLabel + "\", false, " + generic + ")");
                    }
                }
            }
        }

        for (final String valueRow : valueRows) {
            out.print(valueRow);
            out.println(valueRows.getLast().equals(valueRow) ? ';' : ',');
        }

        out.println();
        out.println("    private final int id;");
        out.println("    private final ZclClusterType clusterType;");
        out.println("    private final String label;");
        out.println("    private final boolean received;");
        out.println("    private final boolean generic;");
        out.println();
        out.println("    " + className
                + "(final int id, final ZclClusterType clusterType, final String label, final boolean received, final boolean generic) {");
        out.println("        this.id = id;");
        out.println("        this.clusterType = clusterType;");
        out.println("        this.label = label;");
        out.println("        this.received = received;");
        out.println("        this.generic = generic;");
        out.println("    }");
        out.println();
        out.println("    public int getId() { return id; }");
        out.println("    public ZclClusterType getClusterType() { return clusterType; }");
        out.println("    public String getLabel() { return label; }");
        out.println("    public boolean isReceived() { return received; }");
        out.println("    public boolean isGeneric() { return generic; }");
        out.println("    public String toString() { return label; }");
        out.println("}");

        out.flush();
        out.close();
    }

    private static void generateZclAttributeTypeEnumeration(Context context, String packageRootPrefix,
            File sourceRootPath) throws IOException {

        final String className = "ZclAttributeType";

        final String packageRoot = packageRootPrefix + packageZclProtocol;
        final String packagePath = getPackagePath(sourceRootPath, packageZclProtocol);
        final File packageFile = getPackageFile(packagePath);

        final PrintWriter out = getClassOut(packageFile, className);

        out.println("package " + packageRoot + ";");
        out.println();
        outputClassJavaDoc(out);
        out.println("public enum " + className + " {");

        boolean first = true;
        final LinkedList<Profile> profiles = new LinkedList<Profile>(context.profiles.values());
        for (final Profile profile : profiles) {
            final LinkedList<Cluster> clusters = new LinkedList<Cluster>(profile.clusters.values());

            for (final Cluster cluster : clusters) {
                for (final Attribute attribute : cluster.attributes.values()) {
                    if (first == false) {
                        out.println(",");
                    }
                    first = false;
                    out.print("    " + attribute.enumName + "(0x" + String.format("%04X", cluster.clusterId) + ", 0x"
                            + String.format("%04X", attribute.attributeId) + ", ZclDataType." + attribute.dataType
                            + ")");
                }
            }
        }
        out.println(";");

        out.println();
        out.println("    private final int clusterId;");
        out.println("    private final int attributeId;");
        out.println("    private final ZclAttributeType attributeType;");
        out.println("    private final String label;");
        out.println("    private final boolean received;");
        out.println("    private final boolean generic;");
        out.println();
        out.println("    " + className + "(final int clusterId, final int attributeId, final ZclDataType dataType) {");
        out.println("        this.id = id;");
        out.println("        this.attributeType = attributeType;");
        out.println("        this.label = label;");
        out.println("        this.received = received;");
        out.println("        this.generic = generic;");
        out.println("    }");
        out.println();
        out.println("    public int getId() { return id; }");
        out.println("    public ZclAttributeType getAttributeType() { return attributeType; }");
        out.println("    public String getLabel() { return label; }");
        out.println("    public boolean isReceived() { return received; }");
        out.println("    public boolean isGeneric() { return generic; }");
        out.println("    public String toString() { return label; }");
        out.println("}");

        out.flush();
        out.close();
    }

    private static void generateZclFieldTypeEnumeration(Context context, String packageRootPrefix, File sourceRootPath)
            throws IOException {
        final String className = "ZclFieldType";

        final String packageRoot = packageRootPrefix + packageZclProtocol;
        final String packagePath = getPackagePath(sourceRootPath, packageZclProtocol);
        final File packageFile = getPackageFile(packagePath);

        final PrintWriter out = getClassOut(packageFile, className);

        out.println("package " + packageRoot + ";");
        out.println();
        outputClassJavaDoc(out);
        out.println("public enum " + className + " {");

        final LinkedList<String> valueRows = new LinkedList<String>();
        final LinkedList<Profile> profiles = new LinkedList<Profile>(context.profiles.values());
        for (final Profile profile : profiles) {
            final LinkedList<Cluster> clusters = new LinkedList<Cluster>(profile.clusters.values());
            for (final Cluster cluster : clusters) {
                final ArrayList<Command> commands = new ArrayList<Command>();
                commands.addAll(cluster.received.values());
                commands.addAll(cluster.generated.values());
                for (final Command command : commands) {
                    final LinkedList<Field> fields = new LinkedList<Field>(command.fields.values());
                    for (final Field field : fields) {
                        valueRows.add("    " + field.fieldType + "(" + field.fieldId + ", ZclCommandType."
                                + command.commandType + ", \"" + field.fieldLabel + "\", ZclDataType." + field.dataType
                                + ")");
                    }
                }
            }
        }

        for (final String valueRow : valueRows) {
            out.print(valueRow);
            out.println(valueRows.getLast().equals(valueRow) ? ';' : ',');
        }

        out.println();
        out.println("    private final int id;");
        out.println("    private final ZclCommandType commandType;");
        out.println("    private final String label;");
        out.println("    private final ZclDataType dataType;");
        out.println();
        out.println("    " + className
                + "(final int id, final ZclCommandType commandType, final String label, final ZclDataType dataType) {");
        out.println("        this.id = id;");
        out.println("        this.commandType = commandType;");
        out.println("        this.label = label;");
        out.println("        this.dataType = dataType;");
        out.println("    }");
        out.println();
        out.println("    public int getId() { return id; }");
        out.println("    public ZclCommandType getCommandType() { return commandType; }");
        out.println("    public String getLabel() { return label; }");
        out.println("    public ZclDataType getDataType() { return dataType; }");
        out.println();
        out.println("}");

        out.flush();
        out.close();
    }

    private static void generateZclCommandClasses(Context context, String packageRootPrefix, File sourceRootPath)
            throws IOException {

        final LinkedList<Profile> profiles = new LinkedList<Profile>(context.profiles.values());
        for (final Profile profile : profiles) {
            final LinkedList<Cluster> clusters = new LinkedList<Cluster>(profile.clusters.values());
            for (final Cluster cluster : clusters) {
                final ArrayList<Command> commands = new ArrayList<Command>();
                commands.addAll(cluster.received.values());
                commands.addAll(cluster.generated.values());
                for (final Command command : commands) {
                    final String packageRoot = packageRootPrefix + packageZclProtocolCommand + "."
                            + cluster.clusterType.replace("_", "").toLowerCase();
                    final String packagePath = getPackagePath(sourceRootPath, packageRoot);
                    final File packageFile = getPackageFile(packagePath);

                    final String className = command.nameUpperCamelCase;
                    final PrintWriter out = getClassOut(packageFile, className);

                    final LinkedList<Field> fields = new LinkedList<Field>(command.fields.values());
                    boolean fieldWithDataTypeList = false;
                    for (final Field field : fields) {
                        if (field.dataTypeClass.startsWith("List")) {
                            fieldWithDataTypeList = true;
                            break;
                        }
                    }

                    out.println("package " + packageRoot + ";");
                    out.println();
                    // out.println("import " + packageRootPrefix + packageZcl + ".ZclCommandMessage;");
                    out.println("import " + packageRootPrefix + packageZcl + ".ZclCommand;");
                    // out.println("import " + packageRootPrefix + packageZcl + ".ZclField;");
                    if (fields.size() > 0) {
                        out.println("import " + packageRootPrefix + packageZcl + ".ZclFieldSerializer;");
                        out.println("import " + packageRootPrefix + packageZcl + ".ZclFieldDeserializer;");
                        out.println("import " + packageRootPrefix + packageZclProtocol + ".ZclDataType;");
                    }
                    // out.println("import " + packageRootPrefix + packageZclProtocol + ".ZclClusterType;");
                    // out.println("import " + packageRootPrefix + packageZclProtocol + ".ZclCommandType;");
                    // if (!fields.isEmpty()) {
                    // out.println("import " + packageRootPrefix + packageZclProtocol + ".ZclFieldType;");
                    // if (fieldWithDataTypeList) {
                    // out.println("import " + packageRootPrefix + packageZclField + ".*;");
                    // }
                    // }

                    out.println();
                    if (fieldWithDataTypeList) {
                        out.println("import java.util.List;");
                    }

                    // out.println("import java.util.Map;");
                    // out.println("import java.util.HashMap;");

                    for (final Field field : fields) {
                        switch (field.dataTypeClass) {
                            case "Integer":
                            case "Boolean":
                            case "Object":
                            case "Long":
                            case "String":
                                continue;
                        }
                        if (field.dataTypeClass.startsWith("List")) {
                            String s = field.dataTypeClass;
                            s = s.substring(s.indexOf("<") + 1);
                            s = s.substring(0, s.indexOf(">"));
                            out.println("import " + packageRootPrefix + packageZclField + "." + s + ";");
                        } else {
                            out.println(
                                    "import " + packageRootPrefix + packageZclField + "." + field.dataTypeClass + ";");
                        }
                    }

                    out.println();
                    out.println("/**");
                    out.println(" * <p>");
                    out.println(" * " + command.commandLabel + " value object class.");

                    if (command.commandDescription != null && command.commandDescription.size() != 0) {
                        out.println(" * <p>");
                        for (String line : command.commandDescription) {
                            out.println(" * " + line);
                        }
                    }

                    out.println(" * <p>");
                    out.println(" * Cluster: <b>" + cluster.clusterName + "</b>. Command is sent <b>"
                            + (cluster.received.containsValue(command) ? "TO" : "FROM") + "</b> the server.");
                    out.println(" * This command is " + ((cluster.clusterType.equals("GENERAL"))
                            ? "a <b>generic</b> command used across the profile."
                            : "a <b>specific</b> command used for the " + cluster.clusterName + " cluster."));

                    if (cluster.clusterDescription.size() > 0) {
                        out.println(" * <p>");
                        for (String line : cluster.clusterDescription) {
                            out.println(" * " + line);
                        }
                    }

                    out.println(" * <p>");
                    out.println(" * Code is auto-generated. Modifications may be overwritten!");

                    out.println(" */");
                    out.println("public class " + className + " extends ZclCommand {");

                    for (final Field field : fields) {
                        out.println("    /**");
                        out.println("     * " + field.fieldLabel + " command message field.");
                        out.println("     */");
                        out.println("    private " + field.dataTypeClass + " " + field.nameLowerCamelCase + ";");
                        out.println();
                    }

                    // if (fields.size() > 0) {
                    // out.println(" static {");
                    // for (final Field field : fields) {
                    // out.println(" fields.put(" + field.fieldId + ", new ZclField(" + field.fieldId
                    // + ", \"" + field.fieldLabel + "\", ZclDataType." + field.dataType + "));");
                    // }
                    // out.println(" }");
                    // out.println();
                    // }

                    out.println("    /**");
                    out.println("     * Default constructor.");
                    out.println("     */");
                    out.println("    public " + className + "() {");
                    // out.println(" setType(ZclCommandType." + command.commandType + ");");
                    out.println("        genericCommand = "
                            + ((cluster.clusterType.equals("GENERAL")) ? "true" : "false") + ";");
                    if (!cluster.clusterType.equals("GENERAL")) {
                        out.println("        clusterId = " + cluster.clusterId + ";");
                    }
                    out.println("        commandId = " + command.commandId + ";");

                    out.println("        commandDirection = "
                            + (cluster.received.containsValue(command) ? "true" : "false") + ";");

                    out.println("    }");
                    // out.println();
                    // out.println(" /**");
                    // out.println(" * Constructor copying field values from command message.");
                    // out.println(" *");
                    // out.println(" * @param fields a {@link Map} containing the value {@link Object}s");
                    // out.println(" */");
                    // out.println(" public " + className + "(final Map<Integer, Object> fields) {");
                    // out.println(" this();");
                    // for (final Field field : fields) {
                    // out.println(" " + field.nameLowerCamelCase + " = (" + field.dataTypeClass
                    // + ") fields.get(" + field.fieldId + ");");
                    // }
                    // out.println(" }");
                    // out.println();
                    // out.println(" @Override");
                    // out.println(" public ZclCommandMessage toCommandMessage() {");
                    // out.println(" final ZclCommandMessage message = super.toCommandMessage();");
                    // for (final Field field : fields) {
                    // out.println(" message.getFields().put(ZclFieldType." + field.fieldType + ","
                    // + field.nameLowerCamelCase + ");");
                    // }
                    // out.println(" return message;");
                    // out.println(" }");

                    if (cluster.clusterType.equals("GENERAL")) {
                        out.println();
                        out.println("    /**");
                        out.println("     * Sets the cluster ID for <i>generic</i> commands. {@link " + className
                                + "} is a <i>generic</i> command.");
                        out.println("     * <p>");
                        out.println(
                                "     * For commands that are not <i>generic</i>, this method will do nothing as the cluster ID is fixed.");
                        out.println(
                                "     * To test if a command is <i>generic</i>, use the {@link #isGenericCommand} method.");
                        out.println("     *");
                        out.println(
                                "     * @param clusterId the cluster ID used for <i>generic</i> commands as an {@link Integer}");

                        out.println("     */");
                        out.println("    @Override");
                        out.println("    public void setClusterId(Integer clusterId) {");
                        out.println("        this.clusterId = clusterId;");
                        out.println("    }");
                    }

                    for (final Field field : fields) {
                        out.println();
                        out.println("    /**");
                        out.println("     * Gets " + field.fieldLabel + ".");
                        out.println("     * @return the " + field.fieldLabel);
                        out.println("     */");
                        out.println("    public " + field.dataTypeClass + " get" + field.nameUpperCamelCase + "() {");
                        out.println("        return " + field.nameLowerCamelCase + ";");
                        out.println("    }");
                        out.println();
                        out.println("    /**");
                        out.println("     * Sets " + field.fieldLabel + ".");
                        out.println("     * @param " + field.nameLowerCamelCase + " the " + field.fieldLabel);
                        out.println("     */");
                        out.println("    public void set" + field.nameUpperCamelCase + "(final " + field.dataTypeClass
                                + " " + field.nameLowerCamelCase + ") {");
                        out.println(
                                "        this." + field.nameLowerCamelCase + " = " + field.nameLowerCamelCase + ";");
                        out.println("    }");

                    }

                    if (fields.size() > 0) {
                        // out.println();
                        // out.println(" @Override");
                        // out.println(" public void setFieldValues(final Map<Integer, Object> values) {");
                        // for (final Field field : fields) {
                        // out.println(" " + field.nameLowerCamelCase + " = (" + field.dataTypeClass
                        // + ") values.get(" + field.fieldId + ");");
                        // }
                        // out.println(" }");

                        out.println();
                        out.println("    @Override");
                        out.println("    public void serialize(final ZclFieldSerializer serializer) {");
                        for (final Field field : fields) {
                            out.println("        serializer.serialize(" + field.nameLowerCamelCase + ", ZclDataType."
                                    + field.dataType + ");");
                        }
                        out.println("    }");

                        out.println();
                        out.println("    @Override");
                        out.println("    public void deserialize(final ZclFieldDeserializer deserializer) {");
                        for (final Field field : fields) {
                            out.println("        " + field.nameLowerCamelCase + " = (" + field.dataTypeClass
                                    + ") deserializer.deserialize(" + "ZclDataType." + field.dataType + ");");
                        }
                        out.println("    }");
                    }

                    out.println();
                    out.println("    @Override");
                    out.println("    public String toString() {");
                    out.println("        final StringBuilder builder = new StringBuilder();");
                    out.println("        builder.append(super.toString());");
                    for (final Field field : fields) {
                        out.println("        builder.append(\", " + field.nameLowerCamelCase + "=\");");
                        out.println("        builder.append(" + field.nameLowerCamelCase + ");");
                    }
                    out.println("        return builder.toString();");
                    out.println("    }");

                    out.println();
                    out.println("}");

                    out.flush();
                    out.close();
                }
            }
        }
    }

    private static String getCommandTypeEnum(final Cluster cluster, final Command command, boolean received) {
        return command.commandType + "(ZclClusterType." + cluster.clusterType + ", " + command.commandId + ", "
                + command.nameUpperCamelCase + ".class" + ", " + "\"" + command.commandLabel + "\", " + received + ")";
    }

    private static void generateZclCommandTypeEnumeration(Context context, String packageRootPrefix,
            File sourceRootPath) throws IOException {
        final String className = "ZclCommandType";

        final String packageRoot = packageRootPrefix + packageZclProtocol;
        final String packagePath = getPackagePath(sourceRootPath, packageZclProtocol);
        final File packageFile = getPackageFile(packagePath);

        final PrintWriter out = getClassOut(packageFile, className);

        out.println("package " + packageRoot + ";");
        out.println();

        out.println("import " + packageRootPrefix + packageZcl + ".ZclCommand;");
        out.println();

        List<String> commandEnum = new ArrayList<String>();

        final LinkedList<Profile> profiles = new LinkedList<Profile>(context.profiles.values());
        for (final Profile profile : profiles) {
            final LinkedList<Cluster> clusters = new LinkedList<Cluster>(profile.clusters.values());
            for (final Cluster cluster : clusters) {
                // Brute force to get the commands in order!
                for (int c = 0; c < 65535; c++) {
                    if (cluster.received.get(c) != null) {
                        out.println("import " + getClusterCommandPackage(packageRootPrefix, cluster) + "."
                                + cluster.received.get(c).nameUpperCamelCase + ";");

                        commandEnum.add(getCommandTypeEnum(cluster, cluster.received.get(c), true));
                    }
                    if (cluster.generated.get(c) != null) {
                        out.println("import " + getClusterCommandPackage(packageRootPrefix, cluster) + "."
                                + cluster.generated.get(c).nameUpperCamelCase + ";");

                        commandEnum.add(getCommandTypeEnum(cluster, cluster.generated.get(c), false));
                    }
                }
            }
        }
        out.println();

        out.println();
        outputClassJavaDoc(out);
        out.println("public enum " + className + " {");
        out.println("    /**");
        out.println("     * Register command types.");
        out.println("     */");

        for (String command : commandEnum) {
            out.print("    " + command);
            out.println((commandEnum.indexOf(command) == commandEnum.size() - 1) ? ';' : ',');
        }
        out.println();

        out.println("    private final int commandId;");
        out.println("    private final ZclClusterType clusterType;");
        out.println("    private final Class<? extends ZclCommand> commandClass;");
        out.println("    private final String label;");
        out.println("    private final boolean received;");
        out.println();
        out.println("    " + className
                + "(final ZclClusterType clusterType, final int commandId, final Class<? extends ZclCommand> commandClass, final String label, final boolean received) {");
        out.println("        this.clusterType = clusterType;");
        out.println("        this.commandId = commandId;");
        out.println("        this.commandClass = commandClass;");
        out.println("        this.label = label;");
        out.println("        this.received = received;");
        out.println("    }");
        out.println();

        out.println("    public ZclClusterType getClusterType() { return clusterType; }");
        out.println("    public int getId() { return commandId; }");
        out.println("    public String getLabel() { return label; }");
        out.println("    public boolean isGeneric() { return clusterType==ZclClusterType.GENERAL; }");
        out.println("    public boolean isReceived() { return received; }");
        out.println("    public Class<? extends ZclCommand> getCommandClass() { return commandClass; }");
        out.println();
        out.println(
                "    public static ZclCommandType getValueById(final ZclClusterType clusterType, final int commandId) {");
        out.println("        for (final ZclCommandType value : values()) {");
        out.println("            if(value.clusterType == clusterType && value.commandId == commandId) {");
        out.println("                return value;");
        out.println("            }");
        out.println("        }");
        out.println("        return null;");
        out.println("    }");
        out.println("}");

        out.flush();
        out.close();
    }

    private static void generateZclClusterClasses(Context context, String packageRootPrefix, File sourceRootPath)
            throws IOException {

        final LinkedList<Profile> profiles = new LinkedList<Profile>(context.profiles.values());
        for (final Profile profile : profiles) {
            final LinkedList<Cluster> clusters = new LinkedList<Cluster>(profile.clusters.values());
            for (final Cluster cluster : clusters) {
                final String packageRoot = packageRootPrefix;
                final String packagePath = getPackagePath(sourceRootPath, packageRoot);
                final File packageFile = getPackageFile(packagePath + (packageZclCluster).replace('.', '/'));

                final String className = "Zcl" + cluster.nameUpperCamelCase + "Cluster";
                final PrintWriter out = getClassOut(packageFile, className);

                final ArrayList<Command> commands = new ArrayList<Command>();
                commands.addAll(cluster.received.values());
                commands.addAll(cluster.generated.values());

                out.println("package " + packageRoot + packageZclCluster + ";");
                out.println();

                Set<String> imports = new HashSet<String>();

                for (final Command command : commands) {
                    final LinkedList<Field> fields = new LinkedList<Field>(command.fields.values());
                    for (final Field field : fields) {
                        if (field.dataTypeClass.startsWith("List")) {
                            imports.add("java.util.List");
                            imports.add(packageRootPrefix + packageZclField + ".*");
                            break;
                        }
                    }

                    // if (!fields.isEmpty()) {
                    // imports.add(packageRootPrefix + packageZclProtocol + ".ZclFieldType");
                    // }
                }

                boolean addAttributeTypes = false;
                boolean readAttributes = false;
                boolean writeAttributes = false;
                for (final Attribute attribute : cluster.attributes.values()) {
                    if (attribute.attributeAccess.toLowerCase().contains("write")) {
                        addAttributeTypes = true;
                        writeAttributes = true;
                    }
                    if (attribute.attributeAccess.toLowerCase().contains("read")) {
                        readAttributes = true;
                    }

                    if ("Calendar".equals(attribute.dataTypeClass)) {
                        imports.add("java.util.Calendar");
                    }
                }

                if (addAttributeTypes) {
                    imports.add("com.zsmartsystems.zigbee.zcl.protocol.ZclDataType");
                }

                imports.add(packageRoot + packageZcl + ".ZclCluster");
                if (cluster.attributes.size() != 0) {
                    imports.add(packageRoot + packageZclProtocol + ".ZclDataType");
                }

                if (!commands.isEmpty()) {
                    imports.add(packageRoot + packageZcl + ".ZclCommand");
                }
                // imports.add(packageRoot + packageZcl + ".ZclCommandMessage");

                // imports.add(packageRoot + ".ZigBeeDestination");
                imports.add(packageRoot + ".ZigBeeDeviceAddress");
                imports.add(packageRoot + ".ZigBeeNetworkManager");
                if (!cluster.attributes.isEmpty() | !commands.isEmpty()) {
                    imports.add(packageRoot + ".CommandResult");
                }
                // imports.add(packageRoot + ".ZigBeeDevice");
                imports.add(packageRoot + packageZcl + ".ZclAttribute");
                imports.add("java.util.Map");
                imports.add("java.util.HashMap");

                if (!cluster.attributes.isEmpty() | !commands.isEmpty()) {
                    imports.add("java.util.concurrent.Future");
                }
                // imports.add("com.zsmartsystems.zigbee.model.ZigBeeType");

                for (final Command command : commands) {
                    imports.add(getClusterCommandPackage(packageRoot, cluster) + "." + command.nameUpperCamelCase);
                }

                List<String> importList = new ArrayList<String>();
                importList.addAll(imports);
                Collections.sort(importList);
                for (final String importClass : importList) {
                    out.println("import " + importClass + ";");
                }

                out.println();
                out.println("/**");
                out.println(" * <b>" + cluster.clusterName + "</b> cluster implementation (<i>Cluster ID "
                        + String.format("0x%04X", cluster.clusterId) + "</i>).");
                if (cluster.clusterDescription.size() != 0) {
                    out.println(" * <p>");
                    for (String line : cluster.clusterDescription) {
                        out.println(" * " + line);
                    }
                }
                out.println(" * <p>");
                out.println(" * Code is auto-generated. Modifications may be overwritten!");

                out.println(" */");
                // outputClassJavaDoc(out);
                out.println("public class " + className + " extends ZclCluster {");

                out.println("    // Cluster ID");
                out.println("    public static final int CLUSTER_ID = " + String.format("0x%04X;", cluster.clusterId));
                out.println();
                out.println("    // Cluster Name");
                out.println("    public static final String CLUSTER_NAME = \"" + cluster.clusterName + "\";");
                out.println();

                if (cluster.attributes.size() != 0) {
                    out.println("    // Attribute constants");
                    for (final Attribute attribute : cluster.attributes.values()) {
                        out.println("    public static final int " + attribute.enumName + " = "
                                + String.format("0x%04X", attribute.attributeId) + ";");
                    }
                    out.println();
                }

                out.println("    // Attribute initialisation");
                out.println("    protected Map<Integer, ZclAttribute> initializeAttributes() {");
                out.println("        Map<Integer, ZclAttribute> attributeMap = new HashMap<Integer, ZclAttribute>("
                        + cluster.attributes.size() + ");");
                out.println();
                if (cluster.attributes.size() != 0) {
                    for (final Attribute attribute : cluster.attributes.values()) {
                        out.println("        attributeMap.put(" + attribute.enumName + ", new ZclAttribute("
                                + attribute.attributeId + ", \"" + attribute.attributeLabel + "\", " + "ZclDataType."
                                + attribute.dataType + ", "
                                + "mandatory".equals(attribute.attributeImplementation.toLowerCase()) + ", "
                                + attribute.attributeAccess.toLowerCase().contains("read") + ", "
                                + attribute.attributeAccess.toLowerCase().contains("write") + ", "
                                + "mandatory".equals(attribute.attributeReporting.toLowerCase()) + "));");
                    }
                }
                out.println();
                out.println("        return attributeMap;");
                out.println("    }");
                out.println();

                out.println("    /**");
                out.println("     * Default constructor.");
                out.println("     */");
                out.println("    public " + className
                        + "(final ZigBeeNetworkManager zigbeeManager, final ZigBeeDeviceAddress zigbeeAddress) {");
                out.println("        super(zigbeeManager, zigbeeAddress, CLUSTER_ID, CLUSTER_NAME);");
                out.println("    }");
                out.println();

                for (final Attribute attribute : cluster.attributes.values()) {
                    DataTypeMap zclDataType = ZclDataType.getDataTypeMapping().get(attribute.dataType);

                    if (attribute.attributeAccess.toLowerCase().contains("write")) {
                        out.println();
                        outputAttributeJavaDoc(out, "Set", attribute, zclDataType);
                        out.println("    public Future<CommandResult> set"
                                + attribute.nameUpperCamelCase.replace("_", "") + "(final Object value) {");
                        out.println("        return write(attributes.get(" + attribute.enumName + "), value);");
                        out.println("    }");
                    }

                    if (attribute.attributeAccess.toLowerCase().contains("read")) {
                        outputAttributeJavaDoc(out, "Get", attribute, zclDataType);
                        out.println("    public Future<CommandResult> get"
                                + attribute.nameUpperCamelCase.replace("_", "") + "Async() {");
                        out.println("        return read(attributes.get(" + attribute.enumName + "));");
                        out.println("    }");
                        out.println();
                        outputAttributeJavaDoc(out, "Synchronously get", attribute, zclDataType);
                        out.println("    public " + attribute.dataTypeClass + " get"
                                + attribute.nameUpperCamelCase.replace("_", "") + "() {");
                        out.println("        return (" + attribute.dataTypeClass + ") readSync(attributes.get("
                                + attribute.enumName + "));");
                        out.println("    }");
                    }

                    if (attribute.attributeAccess.toLowerCase().contains("read")
                            && attribute.attributeReporting.toLowerCase().equals("mandatory")) {
                        out.println();
                        outputAttributeJavaDoc(out, "Set reporting for", attribute, zclDataType);
                        if (zclDataType.analogue) {
                            out.println("    public Future<CommandResult> set" + attribute.nameUpperCamelCase
                                    + "Reporting(final int minInterval, final int maxInterval, final Object reportableChange) {");
                            out.println("        return setReporting(attributes.get(" + attribute.enumName
                                    + "), minInterval, maxInterval, reportableChange);");
                        } else {
                            out.println("    public Future<CommandResult> set" + attribute.nameUpperCamelCase
                                    + "Reporting(final int minInterval, final int maxInterval) {");
                            out.println("        return setReporting(attributes.get(" + attribute.enumName
                                    + "), minInterval, maxInterval);");
                        }
                        out.println("    }");
                    }
                }

                for (final Command command : commands) {
                    out.println();
                    out.println("    /**");
                    out.println("     * The " + command.commandLabel);
                    if (command.commandDescription.size() != 0) {
                        out.println("     * <p>");
                        for (String line : command.commandDescription) {
                            out.println("     * " + line);
                        }
                    }
                    out.println("     *");

                    final LinkedList<Field> fields = new LinkedList<Field>(command.fields.values());
                    for (final Field field : fields) {
                        out.println("     * @param " + field.nameLowerCamelCase + " {@link " + field.dataTypeClass
                                + "} " + field.fieldLabel);
                    }

                    out.println("     * @return the {@link Future<CommandResult>} command result future");
                    out.println("     */");
                    out.print("    public Future<CommandResult> " + command.nameLowerCamelCase + "(");

                    boolean first = true;
                    for (final Field field : fields) {
                        if (first == false) {
                            out.print(", ");
                        }
                        out.print(field.dataTypeClass + " " + field.nameLowerCamelCase);
                        first = false;
                    }

                    out.println(") {");
                    out.println("        " + command.nameUpperCamelCase + " command = new " + command.nameUpperCamelCase
                            + "();");
                    if (fields.size() != 0) {
                        out.println();
                        out.println("        // Set the fields");
                    }
                    for (final Field field : fields) {
                        out.println("        command.set" + field.nameUpperCamelCase + "(" + field.nameLowerCamelCase
                                + ");");
                    }
                    out.println();
                    out.println("        return send(command);");
                    out.println("    }");
                }

                if (readAttributes) {
                    out.println();
                    out.println("    /**");
                    out.println("     * Add a binding for this cluster to the local node");
                    out.println("     *");
                    out.println("     * @return the {@link Future<CommandResult>} command result future");
                    out.println("     */");
                    out.println("    public Future<CommandResult> bind() {");
                    out.println("        return bind();");
                    out.println("    }");
                }

                if (cluster.received.size() > 0) {
                    out.println();
                    out.println("    @Override");
                    out.println("    public ZclCommand getCommandFromId(int commandId) {");
                    out.println("        switch (commandId) {");
                    for (final Command command : cluster.received.values()) {
                        out.println("            case " + command.commandId + ": // " + command.commandType);
                        out.println("                return new " + command.nameUpperCamelCase + "();");
                    }
                    out.println("            default:");
                    out.println("                return null;");
                    out.println("        }");
                    out.println("    }");
                }

                if (cluster.generated.size() > 0) {
                    out.println();
                    out.println("    @Override");
                    out.println("    public ZclCommand getResponseFromId(int commandId) {");
                    out.println("        switch (commandId) {");
                    for (final Command command : cluster.generated.values()) {
                        out.println("            case " + command.commandId + ": // " + command.commandType);
                        out.println("                return new " + command.nameUpperCamelCase + "();");
                    }
                    out.println("            default:");
                    out.println("                return null;");
                    out.println("        }");
                    out.println("    }");
                }

                out.println("}");

                out.flush();
                out.close();
            }
        }
    }

    private static void outputAttributeJavaDoc(PrintWriter out, String type, Attribute attribute,
            DataTypeMap zclDataType) {
        out.println();
        out.println("    /**");
        out.println("     * <p>");
        out.println("     * " + type + " the <i>" + attribute.attributeLabel + "</i> attribute [attribute ID <b>"
                + attribute.attributeId + "</b>].");
        if (attribute.attributeDescription.size() != 0) {
            out.println("     * <p>");
            for (String line : attribute.attributeDescription) {
                out.println("     * " + line);
            }
        }
        if ("Synchronously get".equals(type)) {
            out.println("     * <p>");
            out.println("     * This method will block until the response is received or a timeout occurs.");
        }
        out.println("     * <p>");
        out.println("     * The attribute is of type {@link " + attribute.dataTypeClass + "}.");
        out.println("     * <p>");
        out.println("     * The implementation of this attribute by a device is "
                + attribute.attributeImplementation.toUpperCase());
        out.println("     *");
        if ("Set reporting for".equals(type)) {
            out.println("     * @param minInterval {@link int} minimum reporting period");
            out.println("     * @param maxInterval {@link int} maximum reporting period");
            if (zclDataType.analogue) {
                out.println("     * @param reportableChange {@link Object} delta required to trigger report");
            }
        } else if ("Set".equals(type)) {
            out.println("     * @param " + attribute.nameLowerCamelCase + " the {@link " + attribute.dataTypeClass
                    + "} attribute value to be set");
        }

        if ("Synchronously get".equals(type)) {
            out.println("     * @return the {@link " + attribute.dataTypeClass + "} attribute value, or null on error");
        } else {
            out.println("     * @return the {@link Future<CommandResult>} command result future");
        }
        out.println("     */");
    }

    private static PrintWriter getClassOut(File packageFile, String className) throws FileNotFoundException {
        final File classFile = new File(packageFile + File.separator + className + ".java");
        System.out.println("Generating: " + classFile.getAbsolutePath());
        final FileOutputStream fileOutputStream = new FileOutputStream(classFile, false);
        return new PrintWriter(fileOutputStream);
    }

    public static List<String> splitString(String msg, int lineSize) {
        List<String> res = new ArrayList<String>();

        Pattern p = Pattern.compile("\\b.{1," + (lineSize - 1) + "}\\b\\W?");
        Matcher m = p.matcher(msg);

        while (m.find()) {
            System.out.println(m.group().trim()); // Debug
            res.add(m.group());
        }
        return res;
    }

    private static String getClusterCommandPackage(String packageRoot, Cluster cluster) {
        return packageRoot + packageZclProtocolCommand + "." + cluster.clusterType.replace("_", "").toLowerCase();
    }

    private static void generateZdpCommandClasses(Context context, String packageRootPrefix, File sourceRootPath)
            throws IOException {

        final LinkedList<Profile> profiles = new LinkedList<Profile>(context.profiles.values());
        for (final Profile profile : profiles) {
            final LinkedList<Cluster> clusters = new LinkedList<Cluster>(profile.clusters.values());
            for (final Cluster cluster : clusters) {
                final ArrayList<Command> commands = new ArrayList<Command>();
                commands.addAll(cluster.received.values());
                commands.addAll(cluster.generated.values());
                for (final Command command : commands) {
                    final String packageRoot = packageRootPrefix + packageZdpCommand;
                    final String packagePath = getPackagePath(sourceRootPath, packageRoot);
                    final File packageFile = getPackageFile(packagePath);

                    final String className = command.nameUpperCamelCase;
                    final PrintWriter out = getClassOut(packageFile, className);

                    final LinkedList<Field> fields = new LinkedList<Field>(command.fields.values());
                    boolean fieldWithDataTypeList = false;
                    for (final Field field : fields) {
                        if (field.dataTypeClass.startsWith("List")) {
                            fieldWithDataTypeList = true;
                            break;
                        }
                    }

                    out.println("package " + packageRoot + ";");
                    out.println();
                    out.println("import " + packageRootPrefix + packageZcl + ".ZclCommandMessage;");
                    out.println("import " + packageRootPrefix + packageZdp + ".ZcoCommand;");
                    out.println("import " + packageRootPrefix + packageZclProtocol + ".ZclCommandType;");
                    // if (!fields.isEmpty()) {
                    // out.println("import " + packageRootPrefix + packageZclProtocol + ".ZclFieldType;");
                    // if (fieldWithDataTypeList) {
                    // out.println("import " + packageRootPrefix + packageZclField + ".*;");
                    // }
                    // }

                    out.println();
                    if (fieldWithDataTypeList) {
                        out.println("import java.util.List;");
                    }
                    out.println("import java.util.Map;");

                    out.println();
                    out.println("/**");
                    out.println(" * " + command.commandLabel + " value object class.");

                    out.println(" * ");
                    for (String line : command.commandDescription) {
                        out.println(" * " + line);
                    }

                    out.println(" * ");
                    out.println(" * Cluster: " + cluster.clusterName);
                    for (String line : cluster.clusterDescription) {
                        out.println(" * " + line);
                    }

                    out.println(" * ");
                    out.println(" * Code is autogenerated. Modifications may be overwritten!");

                    out.println(" */");
                    out.println("public class " + className + " extends ZdoCommand {");

                    for (final Field field : fields) {
                        out.println("    /**");
                        out.println("     * " + field.fieldLabel + " command message field.");
                        out.println("     */");
                        out.println("    private " + field.dataTypeClass + " " + field.nameLowerCamelCase + ";");
                    }

                    out.println();
                    out.println("    /**");
                    out.println("     * Default constructor setting the command type field.");
                    out.println("     */");
                    out.println("    public " + className + "() {");
                    out.println("        setType(ZclCommandType." + command.commandType + ");");
                    out.println("    }");
                    out.println();
                    out.println("    /**");
                    out.println("     * Constructor copying field values from command message.");
                    out.println("     * @param fields a {@link Map} containing the {@link ZclField}s");
                    out.println("     */");
                    out.println("    public " + className + "(final Map<Integer, ZclField> fields) {");
                    out.println("        super(message);");
                    for (final Field field : fields) {
                        out.println("        this." + field.nameLowerCamelCase + " = (" + field.dataTypeClass
                                + ") message.getFields().get(ZclFieldType." + field.fieldType + ");");
                    }
                    out.println("    }");
                    out.println();
                    out.println("    @Override");
                    out.println(
                            "    public ZclCommandMessage toCommandMessage() { // TODO: Change to ZdoCommandMessage?");
                    out.println("        final ZclCommandMessage message = super.toCommandMessage();");
                    for (final Field field : fields) {
                        out.println("        message.getFields().put(ZclFieldType." + field.fieldType + ","
                                + field.nameLowerCamelCase + ");");
                    }
                    out.println("        return message;");
                    out.println("    }");
                    for (final Field field : fields) {
                        out.println();
                        out.println("    /**");
                        out.println("     * Gets " + field.fieldLabel + ".");
                        out.println("     * @return the " + field.fieldLabel);
                        out.println("     */");
                        out.println("    public " + field.dataTypeClass + " get" + field.nameUpperCamelCase + "() {");
                        out.println("        return " + field.nameLowerCamelCase + ";");
                        out.println("    }");
                        out.println();
                        out.println("    /**");
                        out.println("     * Sets " + field.fieldLabel + ".");
                        out.println("     * @param " + field.nameLowerCamelCase + " the " + field.fieldLabel);
                        out.println("     */");
                        out.println("    public void set" + field.nameUpperCamelCase + "(final " + field.dataTypeClass
                                + " " + field.nameLowerCamelCase + ") {");
                        out.println(
                                "        this." + field.nameLowerCamelCase + " = " + field.nameLowerCamelCase + ";");
                        out.println("    }");
                    }

                    out.println();
                    out.println("    @Override");
                    out.println("    public String toString() {");
                    out.println("        final StringBuilder builder = new StringBuilder();");
                    out.println("        builder.append(super.toString());");
                    for (final Field field : fields) {
                        out.println("        builder.append(\", " + field.nameLowerCamelCase + "=");
                        out.println("        builder.append(" + field.nameLowerCamelCase + ");");
                    }
                    out.println("        return builder.toString();");
                    out.println("    }");

                    out.println();
                    out.println("}");

                    out.flush();
                    out.close();
                }
            }
        }
    }
}
