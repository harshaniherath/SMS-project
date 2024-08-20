import java.util.Scanner;

class StudentMarks {
    private String stId;
    private String stName;
    private int pfMarks;
    private int dbMarks;
    private int totalMarks;

    StudentMarks(String stId, String stName) {
        this.stId = stId;
        this.stName = stName;
        this.dbMarks = -1;
        this.pfMarks = -1;
        this.totalMarks = -2;
    }

    StudentMarks(String stId, String stName, int pfMarks, int dbMarks) {
        this.stId = stId;
        this.stName = stName;
        this.dbMarks = dbMarks;
        this.pfMarks = pfMarks;
        this.totalMarks = this.dbMarks + this.pfMarks;

    }

    public String getStId() {
        return this.stId;
    }

    public String getStName() {
        return this.stName;
    }

    public int getPfMarks() {
        return this.pfMarks;
    }

    public int getDbMarks() {
        return this.dbMarks;
    }

    public int getTotalMarks() {
        return this.totalMarks;
    }

    public void setStId(String stId) {
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public void setPfMarks(int pfMarks) {
        this.pfMarks = pfMarks;
    }

    public void setDbMarks(int dbMarks) {
        this.dbMarks = dbMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }

    public static StudentMarks[] studentsArray = new StudentMarks[0];

    public static Scanner input = new Scanner(System.in);

    public static void main(String args[]) {
        printMenu();
    }

    public static void printMenu() {
        System.out
                .println("\t-----------------------------------------------------------------------------------------");
        System.out.printf("\t|%63s %22s %1s%n", "WELCOME TO GDSE MARKS MANAGEMENT SYSTEM", "", "|");
        System.out
                .println("\t-----------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("\t[1] Add New Student \t\t\t\t[2] Add New Student With Marks");
        System.out.println("\t[3] Add Marks \t\t\t\t\t[4] Update Student Details");
        System.out.println("\t[5] Update Marks \t\t\t\t[6] Delete Student");
        System.out.println("\t[7] Print Student Details\t\t\t[8] Print Student Ranks");
        System.out.println("\t[9] Best in Programming Fundamentals\t\t[10] Best in Database Management System");
        System.out.print("\n\tEnter an option to continue > ");
        int op = input.nextInt();
        clearConsole();
        switch (op) {
            case 1:
                stDetails();
                break;
            case 2:
                stDetailsWithMarks();
                break;
            case 3:
                addMarks();
                break;
            case 4:
                updatestDetails();
                break;
            case 5:
                updateMarks();
                break;
            case 6:
                deleteSt();
                break;
            case 7:
                printStDetails();
                break;
            case 8:
                printRanks();
                break;
            case 9:
                getBestPF();
                break;
            case 10:
                getBestDB();
                break;
            default:
                printMenu();
        }
    }

    public static void stDetails() {
        System.out
                .println("\t-----------------------------------------------------------------------------------------");
        System.out.printf("\t|%58s %27s %1s%n", "ADD NEW STUDENT", "", "|");
        System.out
                .println("\t-----------------------------------------------------------------------------------------");
        System.out.println();

        StudentMarks[] tempstudentsArray = new StudentMarks[studentsArray.length + 1];
        for (int i = 0; i < studentsArray.length; i++) {
            tempstudentsArray[i] = studentsArray[i];
        }

        System.out.print("\tEnter Student ID   : ");
        String Id = input.next();
        String stId = checkstID(Id);

        System.out.print("\tEnter Student Name : ");
        String stName = input.next();

        StudentMarks tempStudent = new StudentMarks(stId, stName);

        tempstudentsArray[tempstudentsArray.length - 1] = tempStudent;

        studentsArray = tempstudentsArray;

        System.out.print("\n\tStudent has been added successfully. Do you want to add a new student (Y/n) : ");
        char ans = input.next().charAt(0);
        checkYesorNo(ans, 1);
    }

    public static String checkstID(String stId) {
        int exist = 0;
        while (exist != -1) {
            if (isIdInArray(stId)) {
                System.out.println("\tThe Student ID already exists");
                System.out.println();
                System.out.print("\tEnter Student ID   : ");
                stId = input.next();
                exist = 0;
            } else {
                exist = -1;
            }
        }
        return stId;
    }

    public static boolean isIdInArray(String stId) {
        L1: for (int i = 0; i < studentsArray.length; i++) {
            if (stId.equalsIgnoreCase(studentsArray[i].getStId())) {
                return true;
            } else {
                continue L1;
            }
        }
        return false;
    }

    public static void stDetailsWithMarks() {
        System.out
                .println("\t-----------------------------------------------------------------------------------------");
        System.out.printf("\t|%58s %27s %1s%n", "ADD NEW STUDENT WITH MARKS", "", "|");
        System.out
                .println("\t-----------------------------------------------------------------------------------------");
        System.out.println();

        StudentMarks[] tempstudentsArray = new StudentMarks[studentsArray.length + 1];
        for (int i = 0; i < studentsArray.length; i++) {
            tempstudentsArray[i] = studentsArray[i];
        }

        System.out.print("\tEnter Student ID   : ");
        String Id = input.next();
        String stId = checkstID(Id);

        System.out.print("\tEnter Student Name : ");
        String stName = input.next();

        System.out.println();
        System.out.print("\tProgramming Fundamentals Marks   : ");
        int markspf = input.nextInt();
        int pfMarks = checkMarks(markspf, "Programming Fundamentals");

        System.out.print("\tDatabase Management System Marks : ");
        int marksdb = input.nextInt();
        int dbMarks = checkMarks(marksdb, "Database Management System");

        StudentMarks tempStudent = new StudentMarks(stId, stName, pfMarks, dbMarks);

        tempstudentsArray[tempstudentsArray.length - 1] = tempStudent;

        studentsArray = tempstudentsArray;

        System.out.println();
        System.out.print("\tStudent has been added successfully. Do you want to add a new student (Y/n) : ");
        char ans = input.next().charAt(0);
        checkYesorNo(ans, 2);
    }

    public static int checkMarks(int marks, String name) {
        boolean exist = false;
        while (exist != true) {
            if (marks >= 0 && marks <= 100) {
                exist = true;
            } else {
                System.out.println("\tInvalid marks, please enter correct marks.\n");
                System.out.print("\t" + name + " Marks   : ");
                marks = input.nextInt();
                exist = false;
            }
        }
        return marks;
    }

    public static void addMarks() {
        System.out
                .println("\t-----------------------------------------------------------------------------------------");
        System.out.printf("\t|%47s %38s %1s%n", "ADD MARKS", "", "|");
        System.out
                .println("\t-----------------------------------------------------------------------------------------");
        System.out.println();

        System.out.print("\tEnter Student ID   : ");
        String Id = input.next();
        String stId = checkValidstId(Id);
        int index = 0;
        for (int i = 0; i < studentsArray.length; i++) {
            if (stId.equalsIgnoreCase(studentsArray[i].getStId())) {
                index = i;
            }
        }
        System.out.print("\tStudent Name       : " + studentsArray[index].getStName());

        if (studentsArray[index].getPfMarks() == -1) {

            System.out.println();
            System.out.print("\tProgramming Fundamentals Marks   : ");
            int markspf = input.nextInt();
            int pfMarks = checkMarks(markspf, "Programming Fundamentals");
            studentsArray[index].setPfMarks(pfMarks);

            System.out.print("\tDatabase Management System Marks : ");
            int marksdb = input.nextInt();
            int dbMarks = checkMarks(marksdb, "Database Management System");
            studentsArray[index].setDbMarks(dbMarks);
            int totalMarks = pfMarks + dbMarks;
            studentsArray[index].setTotalMarks(totalMarks);

        } else {
            System.out.println(
                    "\n\tThis students marks have been already added. \n\tIf you want to update the marks, please use [4] Update Makrs option ");
            System.out.println();
            System.out.print("\tDo you want to add marks for another student ? (Y/n) : ");
            char ans = input.next().charAt(0);
            checkYesorNo(ans, 3);
        }
        System.out.println();
        System.out.print("\tMarks have been added. Do you to add marks for another student (Y/n) : ");
        char ans = input.next().charAt(0);
        checkYesorNo(ans, 3);
    }

    public static String checkValidstId(String stId) {
        int exist = 0;
        while (exist != -1) {
            if (!isIdInArray(stId)) {
                System.out.print("\tInvalid Student ID. Do you want to search again? (Y/n) ");
                char ans = input.next().charAt(0);
                if (ans == 'Y' || ans == 'y') {
                    System.out.println();
                    System.out.print("\tEnter Student ID   : ");
                    stId = input.next();
                } else {
                    clearConsole();
                    printMenu();
                }
                exist = 0;
            } else {
                exist = -1;
            }
        }
        return stId;
    }

    public static void updatestDetails() {
        System.out
                .println("\t-----------------------------------------------------------------------------------------");
        System.out.printf("\t|%55s %30s %1s%n", "UPDATE STUDENT DETAILS", "", "|");
        System.out
                .println("\t-----------------------------------------------------------------------------------------");
        System.out.println();

        System.out.print("\tEnter Student ID   : ");
        String Id = input.next();
        String stId = checkValidstId(Id);
        int index = 0;
        for (int i = 0; i < studentsArray.length; i++) {
            if (stId.equalsIgnoreCase(studentsArray[i].getStId())) {
                index = i;
            }
        }
        System.out.println("\tStudent Name       : " + studentsArray[index].getStName());

        System.out.print("\tEnter the new Student Name : ");
        String stName = input.next();
        studentsArray[index].setStName(stName);

        System.out.print(
                "\tStudent details has been updated successfully.\n\tDo you want to update another student details? (Y/n) ");
        char ans = input.next().charAt(0);
        checkYesorNo(ans, 4);

    }

    public static void updateMarks() {
        System.out
                .println("\t-----------------------------------------------------------------------------------------");
        System.out.printf("\t|%49s %36s %1s%n", "UPDATE MARKS", "", "|");
        System.out
                .println("\t-----------------------------------------------------------------------------------------");
        System.out.println();

        System.out.print("\tEnter Student ID   : ");
        String Id = input.next();
        String stId = checkValidstId(Id);
        int index = 0;
        for (int i = 0; i < studentsArray.length; i++) {
            if (stId.equalsIgnoreCase(studentsArray[i].getStId())) {
                index = i;
            }
        }
        System.out.println("\tStudent Name       : " + studentsArray[index].getStName());

        if (studentsArray[index].getPfMarks() == -1) {
            System.out.println("\tThis Student marks yet to be added.\n");
            System.out.print("\tDo you want to update the marks of another student? (Y/n) ");
            char ans = input.next().charAt(0);
            checkYesorNo(ans, 5);
        } else {
            System.out.println("\tProgramming Fundamentals Marks   : " + studentsArray[index].getPfMarks());
            System.out.println("\tDatabase Management System Marks : " + studentsArray[index].getDbMarks());

            System.out.print("\tEnter new Programming Fundamentals Marks   : ");
            int markspf = input.nextInt();
            int pfMarks = checkMarks(markspf, "Programming Fundamentals");
            studentsArray[index].setPfMarks(pfMarks);

            System.out.print("\tEnter new Database Management System Marks : ");
            int marksdb = input.nextInt();
            int dbMarks = checkMarks(marksdb, "Database Management System");
            studentsArray[index].setDbMarks(dbMarks);

            System.out.println();
            System.out.print(
                    "\tMarks have been updated successfully. \n\tDo you want to update marks for another student? (Y/n) : ");
            char ans = input.next().charAt(0);
            checkYesorNo(ans, 5);
        }
    }

    public static void deleteSt() {
        System.out
                .println("\t-----------------------------------------------------------------------------------------");
        System.out.printf("\t|%50s %35s %1s%n", "DELETE STUDENT", "", "|");
        System.out
                .println("\t-----------------------------------------------------------------------------------------");
        System.out.println();

        System.out.print("\tEnter Student ID   : ");
        String stId = input.next();
        String stID = checkValidstId(stId);
        int index = 0;
        for (int i = 0; i < studentsArray.length; i++) {
            if (stID.equalsIgnoreCase(studentsArray[i].getStId())) {
                index = i;
            }
        }

        StudentMarks[] tempstudentsArray = new StudentMarks[studentsArray.length - 1];

        L1: for (int i = 0; i < studentsArray.length; i++) {
            if (i < index) {
                tempstudentsArray[i] = studentsArray[i];
            } else if (i == index) {
                continue L1;
            } else {
                tempstudentsArray[i - 1] = studentsArray[i];
            }
        }
        studentsArray = tempstudentsArray;

        System.out.println("\tStudent has been deleted successfully.");
        System.out.print("\tDo you want to delete another student? (Y/n) : ");
        char ans = input.next().charAt(0);
        checkYesorNo(ans, 6);

    }

    public static void printStDetails() {
        System.out
                .println("\t-----------------------------------------------------------------------------------------");
        System.out.printf("\t|%53s %32s %1s%n", "PRINT STUDENT DETAILS", "", "|");
        System.out
                .println("\t-----------------------------------------------------------------------------------------");
        System.out.println();

        for (int i = studentsArray.length; i > 0; i--) {
            int min = studentsArray[0].getTotalMarks(), index = 0;
            StudentMarks s1 = studentsArray[0];
            for (int j = 0; j < i; j++) {
                if (studentsArray[j].getTotalMarks() < min) {
                    min = studentsArray[j].getTotalMarks();
                    s1 = studentsArray[j];
                    index = j;
                }
            }
            studentsArray[index] = studentsArray[i - 1];
            studentsArray[i - 1] = s1;
        }

        System.out.print("\tEnter Student ID   : ");
        String stId = input.next();
        String stID = checkValidstId(stId);
        int index = 0;
        for (int i = 0; i < studentsArray.length; i++) {
            if (stID.equalsIgnoreCase(studentsArray[i].getStId())) {
                index = i;
            }
        }
        System.out.println("\tStudent Name       : " + studentsArray[index].getStName());
        if (studentsArray[index].getTotalMarks() < 0) {
            System.out.println("\tMarks yet to be added.\n");
            System.out.print("\n\tDo you want to search another student details? (Y/n) : ");
            char ans = input.next().charAt(0);
            if (ans == 'Y' || ans == 'y') {
                clearConsole();
                printStDetails();
            } else {
                clearConsole();
                printMenu();
            }
        }

        float avg = (float) studentsArray[index].getTotalMarks() / 2;
        String rank;
        int op = index + 1;
        switch (op) {
            case 1:
                rank = "1(First)";
                break;
            case 2:
                rank = "2(Second)";
                break;
            case 3:
                rank = "3(Third)";
                break;
            default:
                rank = "" + op;
        }

        System.out.println("\t+----------------------------------------------+-------------------------+");
        System.out.printf("\t|%-45s |%25s|%n", "programming Fundermentals Marks", studentsArray[index].getPfMarks());
        System.out.printf("\t|%-45s |%25s|%n", "Database Management System Marks", studentsArray[index].getDbMarks());
        System.out.printf("\t|%-45s |%25s|%n", "Total Marks ", studentsArray[index].getTotalMarks());
        System.out.printf("\t|%-45s |%25s|%n", "Avg.marks ", avg);
        System.out.printf("\t|%-45s |%25s|%n", "Rank ", rank);
        System.out.println("\t+----------------------------------------------+-------------------------+");

        System.out.print("\tDo you want to search another student details? (Y/n) : ");
        char ans = input.next().charAt(0);
        if (ans == 'Y' || ans == 'y') {
            clearConsole();
            printStDetails();
        } else {
            clearConsole();
            printMenu();
        }
    }

    public static void printRanks() {
        System.out
                .println("\t-----------------------------------------------------------------------------------------");
        System.out.printf("\t|%55s %30s %1s%n", "PRINT STUDENT RANKS", "", "|");
        System.out
                .println("\t-----------------------------------------------------------------------------------------");
        System.out.println();

        for (int i = studentsArray.length; i > 0; i--) {
            int min = studentsArray[0].getTotalMarks(), index = 0;
            StudentMarks s1 = studentsArray[0];
            for (int j = 0; j < i; j++) {
                if (studentsArray[j].getTotalMarks() < min) {
                    min = studentsArray[j].getTotalMarks();
                    s1 = studentsArray[j];
                    index = j;
                }
            }
            studentsArray[index] = studentsArray[i - 1];
            studentsArray[i - 1] = s1;
        }

        System.out.println("\t+---------+---------+----------------------------+-------------+------------+");
        System.out.printf("\t|%-8s |%-8s |%-27s |%12s |%11s |%-1s", "Rank", "ID", "Name", "Total Marks", "Avg.Marks",
                "");
        System.out.println();
        System.out.println("\t+---------+---------+----------------------------+-------------+------------+");

        for (int i = 0; i < studentsArray.length; i++) {
            if (studentsArray[i].getTotalMarks() < 0) {
                continue;
            } else {
                float avg = (float) studentsArray[i].getTotalMarks() / 2;
                System.out.format("\t|%-8s |%-8s |%-27s |%12s |%11s |%-1s", (i + 1), studentsArray[i].getStId(),
                        studentsArray[i].getStName(), studentsArray[i].getTotalMarks(), avg, "");
            }
            System.out.println();
        }
        System.out.println("\t+---------+---------+----------------------------+-------------+------------+");

        System.out.print("\tDo you want to go back to main? (Y/n) : ");
        char ans = input.next().charAt(0);
        if (ans == 'Y' || ans == 'y') {
            clearConsole();
            printMenu();
        } else {
            clearConsole();
            printRanks();
        }
    }

    public static void getBestPF() {
        System.out
                .println("\t-----------------------------------------------------------------------------------------");
        System.out.printf("\t|%60s %25s %1s%n", "BEST IN PROGRAMMING FUNDAMENTALS", "", "|");
        System.out
                .println("\t-----------------------------------------------------------------------------------------");
        System.out.println();

        for (int i = studentsArray.length; i > 0; i--) {
            int min = studentsArray[0].getPfMarks(), index = 0;
            StudentMarks s1 = studentsArray[0];
            for (int j = 1; j < i; j++) {
                if (studentsArray[j].getPfMarks() < min) {
                    min = studentsArray[j].getPfMarks();
                    s1 = studentsArray[j];
                    index = j;
                }
            }
            studentsArray[index] = studentsArray[i - 1];
            studentsArray[i - 1] = s1;
        }

        System.out.println("\t+----------------+-----------------------------+--------------+-------------+");
        System.out.printf("\t|%-15s |%-28s |%-13s |%-13s| %-1s", "STUDENT ID", "NAME", "PF Marks", "DB Marks", "");
        System.out.println();
        System.out.println("\t+----------------+-----------------------------+--------------+-------------+");

        for (int i = 0; i < studentsArray.length; i++) {
            if (studentsArray[i].getPfMarks() < 0) {
                continue;
            } else {
                System.out.printf("\t|%-15s |%-28s |%-13s |%-13s| %-1s", studentsArray[i].getStId(),
                        studentsArray[i].getStName(), studentsArray[i].getPfMarks(), studentsArray[i].getDbMarks(), "");
            }
            System.out.println();
        }
        System.out.println("\t+----------------+-----------------------------+--------------+-------------+");

        System.out.print("\tDo you want to go back to main? (Y/n) : ");
        char ans = input.next().charAt(0);
        if (ans == 'Y' || ans == 'y') {
            clearConsole();
            printMenu();
        } else {
            clearConsole();
            getBestPF();
        }
    }

    public static void getBestDB() {
        System.out
                .println("\t-----------------------------------------------------------------------------------------");
        System.out.printf("\t|%60s %25s %1s%n", "BEST IN DATABASE MANAGEMENT SYSTEM", "", "|");
        System.out
                .println("\t-----------------------------------------------------------------------------------------");
        System.out.println();

        for (int i = studentsArray.length; i > 0; i--) {
            int min = studentsArray[0].getDbMarks(), index = 0;
            StudentMarks s1 = studentsArray[0];
            for (int j = 1; j < i; j++) {
                if (studentsArray[j].getDbMarks() < min) {
                    min = studentsArray[j].getDbMarks();
                    s1 = studentsArray[j];
                    index = j;
                }
            }
            studentsArray[index] = studentsArray[i - 1];
            studentsArray[i - 1] = s1;
        }

        System.out.println("\t+----------------+-----------------------------+--------------+-------------+");
        System.out.printf("\t|%-15s |%-28s |%-13s |%-13s| %-1s", "STUDENT ID", "NAME", "DB Marks", "PF Marks", "");
        System.out.println();
        System.out.println("\t+----------------+-----------------------------+--------------+-------------+");

        for (int i = 0; i < studentsArray.length; i++) {
            if (studentsArray[i].getPfMarks() < 0) {
                continue;
            } else {
                System.out.printf("\t|%-15s |%-28s |%-13s |%-13s| %-1s", studentsArray[i].getStId(),
                        studentsArray[i].getStName(), studentsArray[i].getDbMarks(), studentsArray[i].getPfMarks(), "");
            }
            System.out.println();
        }
        System.out.println("\t+----------------+-----------------------------+--------------+-------------+");

        System.out.print("\tDo you want to go back to main? (Y/n) : ");
        char ans = input.next().charAt(0);
        if (ans == 'Y' || ans == 'y') {
            clearConsole();
            printMenu();
        } else {
            clearConsole();
            getBestPF();
        }
    }

    public static void checkYesorNo(char ans, int op) {
        if (ans == 'Y' || ans == 'y') {
            clearConsole();
            switch (op) {
                case 1:
                    stDetails();
                    break;
                case 2:
                    stDetailsWithMarks();
                    break;
                case 3:
                    addMarks();
                    break;
                case 4:
                    updatestDetails();
                    break;
                case 5:
                    updateMarks();
                    break;
                case 6:
                    deleteSt();
                    break;
                case 7:
                    printStDetails();
                    break;
                case 8:
                    printRanks();
                    break;
                case 9:
                    getBestPF();
                    break;
                case 10:
                    getBestDB();
                    break;
                default:
                    printMenu();
            }
        } else {
            clearConsole();
            printMenu();
        }
    }

    public final static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c",
                        "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            e.printStackTrace(); // Handle any exceptions.
        }
    }
}