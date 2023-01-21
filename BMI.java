// imports for the swing elements
import javax.swing.JFrame; //main window
import javax.swing.JLabel; //labels ... static elements
import javax.swing.JTextField; //Text fields to interact with user
import javax.swing.JButton; // Buttons to perform actions
import javax.swing.JTextArea; //Text are wil lbe used as a large display
class BMICalculator {
	
  // Use class variables .. known as fields

  //Declare all the GUI elements
  public static JFrame frmMain;
  public static JLabel lblTitle, lblHeight, lblWeight;
  public static JTextField txtHeight, txtWeight;
  public static JTextArea txtDisplay;
  public static JButton btnCalculate, btnClear;

  //Declare variables
  public static double height, weight, bmiValue;
  public static final String HEALTH_CANADA_MESSAGE = 
  "To clarify risk for each individual,\n" + 
  "other factors such as lifestyle habits,\n" + 
  "fitness level,\n" + 
  "and presence or absence of other health risk conditions\n" +
  " also need to be considered\n";
  public static String bmiResults = "";


  public static void main(String[] args) {
    createAndShowGUI();
    actions();
  }

  public static void createAndShowGUI(){
    //initialize all elements and place them in their containers
    // JFrame
    frmMain = new JFrame();
    //it will close all the resources when the x button is clicked
    frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frmMain.setBounds(10,10,400,400);    //Remove any layout manager
    /* 
      This will allow us to place 
      elements in coordinates of our choice
    */
    frmMain.setLayout(null);
    
    //Add the other elements
    //Title
    lblTitle = new JLabel("BMI Calculator");
    // Set location and size
    lblTitle.setBounds(100, 10, 200, 25);
    //Add the label to the main frame
    frmMain.add(lblTitle);
    
    // Add the other elements
    lblHeight = new JLabel("Enter your height in m:");
    lblHeight.setBounds(10,45, 200, 25);
    frmMain.add(lblHeight);

    txtHeight = new JTextField();
    txtHeight.setBounds(220,45,100,25);
    frmMain.add(txtHeight);

   
    lblWeight = new JLabel("Enter your weight in kg:");
    lblWeight.setBounds(10,80, 200, 25);
    frmMain.add(lblWeight);

    txtWeight = new JTextField();
    txtWeight.setBounds(220,80,100,25);
    frmMain.add(txtWeight);

    btnCalculate = new JButton("Calculate");
    btnCalculate.setBounds(10,120,100,25);
    frmMain.add(btnCalculate);

    btnClear = new JButton("Clear");
    btnClear.setBounds(10,160,100,25);
    frmMain.add(btnClear);

    txtDisplay = new JTextArea();
    txtDisplay.setEditable(false); //disable typing on it
    txtDisplay.setBounds(120, 120, 200, 200);
    frmMain.add(txtDisplay);

    //Will display the frame in the desktop
    frmMain.setVisible(true);
  }

  public static void actions(){
    btnCalculate.addActionListener(ActionEvent -> {
      //Do stuff when this button is pressed
      String strWeight = txtWeight.getText();
      String strHeight = txtHeight.getText();
      if (!validateWeight(strWeight)){
        txtDisplay.setText("Weight information is invalid");
        txtWeight.setText("");
        txtWeight.requestFocus();
      } else if (!validateHeight(strHeight)){
        txtDisplay.setText("Height information is invalid");
        txtHeight.setText("");
        txtHeight.requestFocus();
      } else{
        height = Double.parseDouble(strHeight);
        weight = Double.parseDouble(strWeight); 
        bmiValue = calculateBMI(weight, height);
        bmiResults = " your BMI = " + bmiValue + " kg/m^2\n" + 
                     " You are " + getCategory(bmiValue) + "\n" + 
                     HEALTH_CANADA_MESSAGE;
        txtDisplay.setText(bmiResults);
      }
    });
    btnClear.addActionListener(ActionEvent -> {
      //Do stuff when this button is pressed
      txtHeight.setText("");
      txtWeight.setText("");
      txtDisplay.setText("");
      txtHeight.requestFocus();
    });
  }
  
  public static boolean validateHeight(String strHeight){
    try {
      double temp = Double.parseDouble(strHeight);
      if (temp <= 0 || temp > 3){
        return false;
      }else{
        return true;
      }
    }catch (NumberFormatException e){
      return false;
    }
  }
  public static boolean validateWeight(String strWeight){
    try {
      double temp = Double.parseDouble(strWeight);
      if (temp <= 0 || temp > 300){
        return false;
      }else{
        return true;
      }
    }catch (NumberFormatException e){
      return false;
    }
  }
  public static double calculateBMI(double weight, double height){
    return weight/Math.pow(height,2);
  }
  public static final String getCategory(double bmiValue){
    final double UNDER_WEIGHT = 18.5;
    final double NORMAL_WEIGHT = 24.9;
    final double OVER_WEIGHT = 29.9;
    final double OBESE_CLASS_I = 34.9;
    final double OBESE_CLASS_II = 39.9;
    final String UNDER_WEIGHT_MSG = "Underweight - Increased health problems";
    final String NORMAL_WEIGHT_MSG = "Normal weight - Least health problems";
    final String OVER_WEIGHT_MSG = "Overweight - Increased health problems";
    final String OBESE_CLASS_I_MSG = "Obese class I - High health problems";
    final String OBESE_CLASS_II_MSG = "Obese class II - Very high health problems";
    final String OBESE_CLASS_III_MSG = "Obese class III - Extremely high health problems";
    //If data is invalid return null
    if (bmiValue <= 0 ){
      return null;
    }
    //return appropriate level base on the value of BMI index
    if (bmiValue <= UNDER_WEIGHT ){
      return UNDER_WEIGHT_MSG;
    } else if (bmiValue <= NORMAL_WEIGHT){
      return NORMAL_WEIGHT_MSG;
    } else if (bmiValue <= OVER_WEIGHT){
      return OVER_WEIGHT_MSG;
    } else if (bmiValue <= OBESE_CLASS_I){
      return OBESE_CLASS_I_MSG;
    } else if (bmiValue <= OBESE_CLASS_II){
      return OBESE_CLASS_II_MSG;
    } else {
      return OBESE_CLASS_III_MSG;
    }
  }
}
