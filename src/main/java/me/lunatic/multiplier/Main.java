package me.lunatic.multiplier;

import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;
import com.univocity.parsers.tsv.TsvWriter;
import com.univocity.parsers.tsv.TsvWriterSettings;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    /**
     * Here, you want to specific a list of id's you want to multiply.
     * This includes a few examples, those being AR-Experience, Mora, Both types of Fates, and all Types of Char and Weapon Upgrade Materials.
     * The first value is the item id you want to multiply, the second value is the value to multiply by. eg "1.5F" means "+50%"
     */
    private static final List<Multiplication> MULTIPLICATIONS = List.of(
            new Multiplication("102", 1.25F),
            new Multiplication("202", 1.5F),
            new Multiplication("223", 1.5F),
            new Multiplication("224", 1.5F),
            new Multiplication("104001", 1.3F),
            new Multiplication("104002", 1.3F),
            new Multiplication("104003", 1.3F),
            new Multiplication("104011", 1.3F),
            new Multiplication("104012", 1.3F),
            new Multiplication("104013", 1.3F)
    );

    private static final File IN_FILE = new File("RewardData.txt.original");
    private static final File OUT_FILE = new File("RewardData.txt");

    public static void main(String[] args) throws Exception {
        if (OUT_FILE.exists()) OUT_FILE.delete();
        TsvParser parser = new TsvParser(new TsvParserSettings());
        List<Object[]> rows = new ArrayList<>(parser.parseAll(IN_FILE));
        for (int i = 0; i < rows.size(); i++) {
            String[] row = (String[]) rows.get(i);
            String[] oldRow = row.clone();
            for (int i1 = 7; i1 < row.length - 5; i1 += 2) {
                String rewardID = row[i1];
                try {
                    int rewardAmount = Integer.parseInt(row[i1 + 1]);
                    for (Multiplication mult : MULTIPLICATIONS) {
                        if (rewardID.equals(mult.id())) {
                            row[i1 + 1] = String.valueOf(Math.round(rewardAmount * mult.multValue()));
                        }
                    }
                } catch (NumberFormatException ignored) {
                }
            }
            if (!Arrays.equals(oldRow, row)) {
                System.out.println("[DEBUG] REPLACED ROW:");
                System.out.println("[DEBUG] [OLD]: " + Arrays.toString(oldRow));
                System.out.println("[DEBUG] [NEW]: " + Arrays.toString(row));
                System.out.println("[DEBUG] ----------------------------------------------------------------------------------------");
            }
        }
        System.out.println("[DEBUG] Writing to Final File...");
        TsvWriterSettings settings = new TsvWriterSettings();
        TsvWriter writer = new TsvWriter(OUT_FILE, settings);
        writer.writeRowsAndClose(rows);
        System.out.println("[DEBUG] Done. Enjoy <3");
    }
}
