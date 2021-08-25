package factory;

import models.tools.Chainsaw;
import models.tools.Jackhammer;
import models.tools.Ladder;
import models.tools.Tool;

/**
 * Factory class for building a Tool object based on the toolCode. This hides the underlying classes and only exposes
 * the parent Tool class to the client.
 */
public class ToolFactory {

    /**
     * Creates a Tool object based on the provided tool code.
     *
     * @param toolCode
     * @return
     * @throws Exception
     */
    public static Tool getTool(String toolCode) throws Exception {
        switch (toolCode) {
            case "LADW":
                return new Ladder("Werner", toolCode);
            case "CHNS":
                return new Chainsaw("Stihl", toolCode);
            case "JAKR":
                return new Jackhammer("Rigid", toolCode);
            case "JAKD":
                return new Jackhammer("DeWalt", toolCode);
            default:
                throw new Exception("Invalid Tool code. Tool code does not exist in our system.");
        }
    }
}
