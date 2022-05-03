import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultFrame extends JFrame implements ActionListener {
    private JLabel BMIVal;
    private JLabel weightStatusLabel;
    private JLabel wStatusVal;
    private JLabel idealWeightLabel;
    private JLabel IdealWVal;
    private JPanel ResultPanel;
    private JButton exitButton;
    private JButton againButton;
    private JLabel nameValLabel;
    private JLabel resultForLabel;
    private final double height;
    private final double weight;
    private final double age;
    private final BodyFrame bodyFrame;
    private final String firstName;
    private final String lastName;

    public ResultFrame(double height, double weight, double age , BodyFrame bodyFrame , String firstName, String lastName) {
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.bodyFrame = bodyFrame;
        this.firstName = firstName;
        this.lastName = lastName;
        setTitle("BMI");
        defineIdealWeightVal();
        defineNameField();
        defineResultValues();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("premium-icon-bmi-2117130.png")));
        add(ResultPanel);
        setSize(250,200);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        exitButton.addActionListener(this);
        againButton.addActionListener(this);


    }

    private void defineResultValues(){
        double result = weight/(height*height);
        String wStatusMessage;

        if (result < 15) {
            wStatusMessage = "Anorexic";
        }
        else if (result >= 15 && result < 18.5) {
            wStatusMessage = "Underweight";
        }
        else if (result >= 18.5 && result <= 24.9) {
            wStatusMessage = "Normal";
        }
        else if (result > 24.9 && result <= 29.9) {
            wStatusMessage = "Overweight";
        }
        else if (result > 29.9 && result < 35) {
            wStatusMessage = "Obese";
        }
        else {
            wStatusMessage = "Extreme obese";
        }

        BMIVal.setText(String.format("%.1f", result));
        wStatusVal.setText(wStatusMessage);
    }

    private void defineIdealWeightVal(){
        double slimness = switch (bodyFrame) {
            case SMALL -> 0.9;
            case MEDIUM -> 1.0;
            case LARGE -> 1.1;
        };


        double idealWeight = (((height-1)*100d)+(age/10))*0.9*slimness;

        IdealWVal.setText(String.format("%.1f", idealWeight));
    }

    private void defineNameField() {
        int[] spacesQuantity = countSpaces(firstName, lastName);

        String nameMessage = "";
        if (spacesQuantity[0] > 1 || spacesQuantity[1] > 1 ||
                firstName.length() > 15 || lastName.length() > 15)
        {
            if (firstName.equals("Enter the first name.") && !lastName.equals("Enter the last name.")) {
                if (!(spacesQuantity[1] > 2)) {
                    nameMessage = lastName;
                }
            }
            else if (!firstName.equals("Enter the first name.") && lastName.equals("Enter the last name.")) {
                if (!(spacesQuantity[0] > 2)) {
                    nameMessage = firstName;
                }
            }
            else {
                nameMessage = "John Doe";
            }

        }
        else {
            nameMessage = firstName.concat(" ").concat(lastName);
        }
        nameValLabel.setText(nameMessage);
    }

    private int[] countSpaces(String firstName, String lastName) {
        int[] spacesQuantity = new int[2];
        int firstNameSpacesCounter = 0, lastNameSpacesCounter = 0;
        for (int i = 0; i < firstName.length(); i++) {
            if (firstName.charAt(i) == ' ') {
                firstNameSpacesCounter++;
            }
        }

        for (int i = 0; i < lastName.length(); i++) {
            if (lastName.charAt(i) == ' ') {
                lastNameSpacesCounter++;
            }
        }
        spacesQuantity[0] = firstNameSpacesCounter;
        spacesQuantity[1] = lastNameSpacesCounter;

        return spacesQuantity;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==exitButton){
            System.exit(0);
        }
        else if (e.getSource()==againButton) {
            this.setVisible(false);
            MainFrame mainFrame = new MainFrame();
        }

    }
}
