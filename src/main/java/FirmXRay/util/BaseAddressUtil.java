package FirmXRay.util;

import FirmXRay.main.Constant;
import FirmXRay.main.Logger;
import ghidra.program.model.address.Address;
import ghidra.program.model.listing.Program;
import ghidra.program.model.mem.MemoryAccessException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BaseAddressUtil {


    public static long getBaseAddressFromFile(String programName) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("./base/base.txt"));
            String line = reader.readLine();
            while (line != null) {
                String name = line.split("\t")[0].strip();
                if (name.equals(programName)) {
                    return Integer.parseInt(line.split("\t")[1]);
                }
                line = reader.readLine();
            }

        } catch (IOException e) {
            // not found
            Logger.printE(e.toString());
            return 0;
        }

        Logger.printE("Base not found");
        return -1;
    }

    public static boolean isBaseInFile(String tag) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("./base/base.txt"));
            String line = reader.readLine();
            while (line != null) {
                String name = line.split("\t")[0].strip();
                if (name.equals(tag)) {
                    return true;
                }
                line = reader.readLine();
            }

        } catch (IOException e) {
            // not found
            Logger.printE(e.toString());
            return false;
        }

        return false;
    }

    public static List<Long> getE7Address(Program program) {
        Address current = program.getMinAddress();
        long length = program.getMaxAddress().getUnsignedOffset();

        ArrayList<Long> result = new ArrayList<>();
        while (current.getUnsignedOffset() < length) {
            try {
                if (program.getMemory().getByte(current) == Constant.E7) {
                    result.add(current.getUnsignedOffset());
                }
            } catch (MemoryAccessException ignored) {

            }
            current = current.next();
        }

        return result;
    }


    public static List<Long> getFunctionEntries(Program program) {
        Address current = program.getMinAddress();
        long length = program.getMaxAddress().getUnsignedOffset();

        ArrayList<Long> entry = new ArrayList<>();
        while (current.getUnsignedOffset() < length) {
            if (isFunctionPrologue(program, current)) {
                entry.add(current.getUnsignedOffset()-1);
            }
            current = current.next();
        }
        return entry;
    }


    public static boolean isFunctionPrologue(Program program, Address current) {
        try {
            if (program.getMemory().getByte(current) == Constant.PUSH)
                return true;
            else if (program.getMemory().getByte(current) == Constant.STMFD1) {
                if (program.getMemory().getByte(current.next()) == Constant.STMFD2) {
                    return true;
                }
            }
        } catch (MemoryAccessException e) {
            return false;
        }
        return false;
    }


}
