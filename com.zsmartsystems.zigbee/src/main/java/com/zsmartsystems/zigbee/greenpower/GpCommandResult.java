package com.zsmartsystems.zigbee.greenpower;

public class GpCommandResult {

    /**
     * The response command.
     */
    private final GpCommand response;

    /**
     * Constructor which sets the received response command or null if timeout occurs.
     *
     * @param response the response command.
     */
    public GpCommandResult(final GpCommand response) {
        this.response = response;
    }

    /**
     * Constructor for timeout situations.
     */
    public GpCommandResult() {
        response = null;
    }

    /**
     * Checks whether command execution was successful.
     *
     * @return TRUE if command execution was successful.
     */
    public boolean isSuccess() {
        return !(isTimeout() || isError());
    }

    /**
     * Checks whether command timed out.
     *
     * @return TRUE if timeout occurred
     */
    public boolean isTimeout() {
        return response == null;
    }

    /**
     * Checks if command was successful, or resulted in an error.
     *
     * @return true if the command resulted in an error
     */
    public boolean isError() {
    	return response == null;       
    }

    public <GpCommand> GpCommand getResponse() {
        return (GpCommand) response;
    }
}
