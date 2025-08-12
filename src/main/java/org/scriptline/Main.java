package org.scriptline;

import Xinyuiii.MansionGenerator.piece.Piece;
import Xinyuiii.MansionGenerator.properties.MansionGenerator;
import com.seedfinding.mccore.rand.ChunkRand;
import com.seedfinding.mccore.version.MCVersion;

import java.io.*;
import java.util.Scanner;

class Checker {
    long startSeed, endSeed;
    Checker(long startSeed, long endSeed) {
        this.startSeed = startSeed;
        this.endSeed = endSeed;
    }

    public void run() {
        ChunkRand rand = new ChunkRand();
        MansionGenerator generator = new MansionGenerator(MCVersion.v1_20);
        for (long seed = this.startSeed; seed < this.endSeed; seed++) {
            generator.generateFromCarver(seed, rand);
            int cats = 0;
            for (Piece room : generator.getAllRooms()) {
                if (room.name.equals("Cat statue room")) {
                    cats++;
                }
            }
            if (cats >= 7) {
                System.out.printf("%d %d\n", seed, cats);
            }
        }
    }
}

public class Main {
    public static final MCVersion version = MCVersion.v1_20;

    public static void main(String[] args) throws InterruptedException, IOException {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] parts = input.split(" ");

        long startSeed = Long.parseLong(parts[0]);
        long endSeed = Long.parseLong(parts[1]);

        Checker checker = new Checker(startSeed, endSeed);
        checker.run();
    }
}
