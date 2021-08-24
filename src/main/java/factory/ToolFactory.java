package factory;

import models.tools.Chainsaw;
import models.tools.Jackhammer;
import models.tools.Ladder;
import models.tools.Tool;

public class ToolFactory {

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
