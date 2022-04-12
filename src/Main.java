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

        JSlider slider = new JSlider(SwingConstants.HORIZONTAL, 125135, 613434516, 125135);
        HexColorCalculator hcc = new HexColorCalculator(slider);


        ChangeListener sliderListener = e -> {
            String HexColorCode = hcc.CalculateHexColor();

            LabelHexValue.setText(HexColorCode);
            mainpanel.setBackground(Color.decode(HexColorCode));

            LabelSliderValue.setText("sliderScaledValue: "+hcc.getScaledSliderValue()+"   ");
            LabelRowValue.setText("rowValue: "+hcc.getRow()+"   ");
            LabelColumnValue.setText("columnValue: "+hcc.getCloumn()+"   ");
            LabelCalculatedValue.setText("CalculatedRGBValue: "+hcc.getCalculatedRGBValue()+"   ");
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
}
