import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame implements ActionListener {
    private JPanel mainPanel;
    private JTextField enterTheFirstNameTextField;
    private JTextField enterTheLastNameTextField;
    private JLabel ageLabel;
    private JSlider sliderAge;
    private JLabel ageValLabel;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JLabel genderLabel;
    private JLabel heightLabel;
    private JSlider sliderHeight;
    private JLabel heightValLabel;
    private JRadioButton smallRadioButton;
    private JRadioButton mediumRadioButton;
    private JSlider sliderWeight;
    private JLabel actualWeightLabel;
    private JLabel weightValLabel;
    private JButton exitButton;
    private JButton OKButton;
    private JButton resetButton;
    private JLabel bodyFrameLabel;
    private JRadioButton largeRadioButton;
    private JLabel yearsLabel;
    private JLabel CMLabel;
    private JLabel KGLabel;
    private Gender gender;
    private BodyFrame bodyFrame;

    public static final int WIDTH = 400, HEIGHT = 500;

    public MainFrame(){
        this.gender = Gender.MALE;
        this.bodyFrame = BodyFrame.SMALL;
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("premium-icon-bmi-2117130.png")));
        this.setTitle("BMI Calculator");
        this.add(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setVisible(true);
        this.setResizable(false);

        activateSliders();
        activateButtons();


        enterTheFirstNameTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

                if (enterTheFirstNameTextField.getText().equals("Enter the first name.")) {
                    enterTheFirstNameTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (enterTheFirstNameTextField.getText().equals("")) {
                    enterTheFirstNameTextField.setText("Enter the first name.");
                }
            }
        });


        enterTheLastNameTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

                if (enterTheLastNameTextField.getText().equals("Enter the last name.")) {
                    enterTheLastNameTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (enterTheLastNameTextField.getText().equals("")) {
                    enterTheLastNameTextField.setText("Enter the last name.");
                }
            }
        });

        enterTheFirstNameTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                if (!enterTheFirstNameTextField.hasFocus()) {
                    enterTheFirstNameTextField.setFocusable(true);
                }
            }
        });
        enterTheLastNameTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                if (!enterTheLastNameTextField.hasFocus()) {
                    enterTheLastNameTextField.setFocusable(true);
                }
            }
        });


        OKButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getSource()==OKButton) {
                    calculateBMI();
                }
            }
        });
    }

    private void activateButtons(){
        maleRadioButton.addActionListener(this);
        femaleRadioButton.addActionListener(this);
        smallRadioButton.addActionListener(this);
        mediumRadioButton.addActionListener(this);
        largeRadioButton.addActionListener(this);
        exitButton.addActionListener(this);
        resetButton.addActionListener(this);
    }

    private void activateSliders() {
        sliderAge.addChangeListener(this::stateChanged);
        sliderHeight.addChangeListener(this::stateChanged);
        sliderWeight.addChangeListener(this::stateChanged);
    }



    public void stateChanged(ChangeEvent e) {
        ageValLabel.setText(String.valueOf(sliderAge.getValue()));
        heightValLabel.setText(String.valueOf(sliderHeight.getValue()));
        weightValLabel.setText(String.valueOf(sliderWeight.getValue()));
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == maleRadioButton) {
            gender = Gender.MALE;
        }
        else if (e.getSource() == femaleRadioButton) {
            gender = Gender.FEMALE;
        }

        if (e.getSource() == smallRadioButton) {
            bodyFrame = BodyFrame.SMALL;
        }
        else if(e.getSource() == mediumRadioButton) {
            bodyFrame = BodyFrame.MEDIUM;
        }
        else if(e.getSource() == largeRadioButton) {
            bodyFrame = BodyFrame.LARGE;
        }

        if (e.getSource() == exitButton) {
            System.exit(0);
        }
        if (e.getSource() == resetButton) {
            resetUserData();
        }
        if (e.getSource() == OKButton) {
            calculateBMI();
        }
    }

    private void calculateBMI() {
        double weight = sliderWeight.getValue();
        double height = (sliderHeight.getValue()/100d);
        String firstName = enterTheFirstNameTextField.getText();
        String lastName = enterTheLastNameTextField.getText();
        this.setVisible(false);
        ResultFrame resultFrame = new ResultFrame(height, weight, sliderAge.getValue(), bodyFrame, firstName, lastName);



/*        double weight = sliderWeight.getValue();
        double height = (sliderHeight.getValue()/100d);
        double result = weight/(height*height);
        System.out.printf("Your BMI: %.1f", result);
        System.out.println();*/


    }

    private void resetUserData(){
        enterTheFirstNameTextField.setText("Enter the first name.");
        enterTheLastNameTextField.setText("Enter the last name.");
        sliderAge.setValue(65);
        sliderHeight.setValue(115);
        sliderWeight.setValue(150);
        maleRadioButton.setSelected(true);
        smallRadioButton.setSelected(true);
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
    }

}
