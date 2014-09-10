package ph.edu.ust.jakearroyo.soccr101;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class DbGen {

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1000, "ph.edu.ust.jakearroyo.soccr101.db");

        addOrg(schema);

        // Generate classes for sqlite using greendao
        new DaoGenerator().generateAll(schema, "./app/src/main/java");
    }

    private static void addOrg(Schema schema){
        Entity org = schema.addEntity("Org");

        org.addIdProperty(); // Add primary key Id
        org.addStringProperty("name").notNull(); // Column name: "name", nullable: false, type: string
        org.addStringProperty("description").notNull(); // Column name: "description", nullable: false, type: string
        org.addStringProperty("logo"); // Column name: "description", type: string
    }
}
