/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package taxcalculationsca;

import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author lizam
 */
public class TaxCalculationsCA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        // TODO code application logic here
        IOUtils scan = new IOUtils();

        DatabaseWriter dbWriter = new DatabaseWriter();
        DatabaseReader dbReader = new DatabaseReader();
        ApplicationMenu menu = new ApplicationMenu(dbWriter, dbReader);
        menu.run();
    }

    public static class ApplicationMenu {

        private DatabaseWriter dbWriter;
        private DatabaseReader dbReader;
        private IOUtils scan;

        public ApplicationMenu(DatabaseWriter dbWriter, DatabaseReader dbReader) {
            this.dbWriter = dbWriter;
            this.dbReader = dbReader;
            this.scan = new IOUtils();
        }

        public void run() throws SQLException {
            while (true) {
                System.out.println("1. Sign Up");
                System.out.println("2. Sign In");
                System.out.println("3. Exit");
                int choice = scan.getUserInt("Choose an option:", 1, 3);

                User loggedInUser = null;

                if (loggedInUser == null) {
                    switch (choice) {
                        case 1:
                            signUpNewUser();
                            break;
                        case 2:
                            loggedInUser = signInUser();
                            break;
                        case 3:
                            System.exit(0);
                            break;
                    }
                } else {

                    System.out.println("1. View My data.");
                    System.out.println("2. Edit my data.");
                    System.out.println("3. Calculate tax");
                    System.out.println("4. Exit");
                    int regularChoice = scan.getUserInt("Choose an option:", 1, 4);
                    switch (regularChoice) {
                        case 1:
                            System.out.println(loggedInUser.getAllData());
                            break;
                        case 2:
                            System.out.println("1. User Name: " + loggedInUser.getUserName() + "\n");
                            System.out.println("2. First Name: " + loggedInUser.getFirstName() + "\n");
                            System.out.println("3. Last Name: " + loggedInUser.getLastName() + "\n");
                            System.out.println("4. Pass Word: " + loggedInUser.getPassword() + "\n");
                            System.out.println("5. Position: " + loggedInUser.getPosition() + "\n");
                            System.out.println("6. BithDate: " + loggedInUser.getBirthDate().toString() + "\n");
                            System.out.println("7. E-mail: " + loggedInUser.getEmail() + "\n");
                            int dataChoice = scan.getUserInt("Choose an option for modify:", 1, 4);

                            switch (dataChoice) {
                                case 1:
                                    // Modificar o nome de usuário
                                    String newUserName = scan.getUserText("Enter a new UserName");
                                    loggedInUser.setUserName(newUserName);
                                    System.out.println("User Name updated to: " + newUserName);
                                    break;
                                case 2:
                                    // Modificar o primeiro nome
                                    String newFirstName = scan.getUserText("Enter a new First Name");
                                    loggedInUser.setFirstName(newFirstName);
                                    System.out.println("First Name updated to: " + newFirstName);
                                    break;
                                case 3:
                                    // Modificar o sobrenome
                                    String newLastName = scan.getUserText("Enter a new Last Name");
                                    loggedInUser.setLastName(newLastName);
                                    System.out.println("Last Name updated to: " + newLastName);
                                    break;
                                case 4:
                                    // Modificar a senha
                                    String newPassword = scan.getUserText("Enter a new Password");
                                    loggedInUser.setPassword(newPassword);
                                    System.out.println("Password updated to: " + newPassword);
                                    break;
                                case 5:
                                    // Modificar a posição
                                    String newPosition = scan.getUserText("Enter a new Position");
                                    loggedInUser.setPosition(newPosition);
                                    System.out.println("Position updated to: " + newPosition);
                                    break;
                                case 6:
                                    // Modificar a data de nascimento
                                    Date newBirthDate = scan.getUserData("Enter a new Birth Date.");
                                    loggedInUser.setBirthDate(newBirthDate);
                                    System.out.println("Birth Date updated to: " + newBirthDate);
                                    break;
                                case 7:
                                    // Modificar o e-mail
                                    String newEmail = scan.getUserText("Enter a new Email");
                                    loggedInUser.setEmail(newEmail);
                                    System.out.println("Email updated to: " + newEmail);
                                    break;
                                default:
                                    System.out.println("Invalid choice");
                                    break;
                            }

                            break;
                        case 3:
                            loggedInUser.IcommingTaxCalculator();
                            loggedInUser.USCTaxCalculator();
                            loggedInUser.PRSCI();
                            loggedInUser.NetPaymentCalculator();
                        default:
                            throw new AssertionError();
                    }

                }
            }
        }

        private void signUpNewUser() throws SQLException {

            String userName = scan.getUserText("Enter your username:");
            String firstName = scan.getUserText("Enter your firstName:");
            String lastName = scan.getUserText("Enter your lasName:");
            String position = scan.getUserText("Enter your position:");
            Date userBirthDate = scan.getUserData("Enter your birthDate:");
            String userEmail = scan.getUserText("Enter your E-mail:");
            String password = scan.getUserText("Enter your password:");
            commonVariables.Type userType = commonVariables.Type.Regular;
            int userMarital = scan.getUserInt("Enter yout marital status. 1 for Single, 2 for Married and 3 for widowed", 1, 3);
            commonVariables.MaritalStatus userMaritalStatus;

            switch (userMarital) {
                case 1:
                    userMaritalStatus = commonVariables.MaritalStatus.Single;
                case 2:
                    userMaritalStatus = commonVariables.MaritalStatus.Married;
                case 3:
                    userMaritalStatus = commonVariables.MaritalStatus.widowed;

                    break;
                default:
                    throw new AssertionError();
            }

            boolean success = dbWriter.addUserDatabase(userName, firstName, lastName, position, userBirthDate, userEmail, password, userType, userMaritalStatus);
            if (success) {
                System.out.println("User registered successfully.");
            } else {
                System.out.println("Failed to register user.");
            }
        }

        private User signInUser() throws SQLException {
            String userName = scan.getUserText("Enter your user name.");
            String userPassWord = scan.getUserText("Enter your passWord");

            User loggedInUser = dbReader.getUser(userName, userPassWord);

            return loggedInUser;
        }

    }
}
