import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class Main {
    public static void main(String[] args){

        JFrame jframe = new JFrame();
        JPanel mainpanel = new JPanel();
        JLabel LabelHexValue = new JLabel();
        JLabel LabelSliderValue = new JLabel();
        JLabel LabelRowValue = new JLabel();
        JLabel LabelColumnValue = new JLabel();
        JLabel LabelCalculatedValue = new JLabel();
        JSlider slider = new JSlider(SwingConstants.HORIZONTAL, 0, 234*255, 0);


        ChangeListener sliderListener = e -> {
            String[] ValueArray = CalculateHexColor(slider.getValue(), slider.getMinimum(), slider.getMaximum());

            LabelHexValue.setText(ValueArray[0]);
            mainpanel.setBackground(Color.decode(ValueArray[0]));

            LabelSliderValue.setText("sliderValue: "+ValueArray[1]);
            LabelRowValue.setText("rowValue: "+ValueArray[2]);
            LabelColumnValue.setText("columnValue: "+ValueArray[3]);
            LabelCalculatedValue.setText("CalculatedValue: "+ValueArray[4]);
        };
        slider.addChangeListener(sliderListener);
        slider.setPreferredSize(new Dimension(1100, 20));

        jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
        jframe.add(mainpanel);
        jframe.setSize(1200, 800);
        jframe.setResizable(false);

        mainpanel.setLayout(new FlowLayout());
        mainpanel.setBackground(Color.decode("#ff0000"));
        mainpanel.add(slider);
        mainpanel.add(LabelHexValue);
        mainpanel.add(LabelSliderValue);
        mainpanel.add(LabelRowValue);
        mainpanel.add(LabelColumnValue);
        mainpanel.add(LabelCalculatedValue);

        jframe.setVisible(true);
    }

    private static int cutDecimal(float floatvalue){
        return (int) floatvalue;
    }

    private static String[] CalculateHexColor(int IntHexColor, int Min, int Max){



        float FloatHexColor = IntHexColor;
        int span = Max-Min;
        float scale = span/(6*255);
        FloatHexColor = FloatHexColor/scale;
        IntHexColor = cutDecimal(FloatHexColor);

        String[][] CaseArray = {
                {"255", "void", "0"},
                {"void", "255", "0"},
                {"0", "255", "void"},
                {"0", "void", "255"},
                {"void", "0", "255"},
                {"255", "0", "void"},
        };

        int row = 0;
        for(int i=0; i<=5; i++){
            if(IntHexColor <= (255*(i+1))){
                row = i;
                break;
            }
        }

        int column = 1;
        for(int i=0; i<row; i++){
            column--;
            if(column == -1){
                column = 2;
            }
        }

        if((row+1)%2 == 0){
            CaseArray[row][column] = Integer.toString(255-(IntHexColor-(255*row)));
        }else{
            CaseArray[row][column] = Integer.toString(IntHexColor-(255*row));
        }
        String CalculatedValue = CaseArray[row][column];

        StringBuilder sb = new StringBuilder();
        sb.append("#");
        for(int i=0; i<3; i++){
            if(CaseArray[row][i].equals("0")){
                sb.append("00");
            }else{
                if(Integer.parseInt(CaseArray[row][i]) < 16){
                    sb.append("0");
                }
                sb.append(Integer.toHexString(Integer.parseInt(CaseArray[row][i])));
            }
        }
        return new String[]{String.valueOf(sb), Integer.toString(IntHexColor), Integer.toString(row), Integer.toString(column), CalculatedValue};
    }
}
