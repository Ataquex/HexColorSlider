import javax.swing.*;

public class HexColorCalculator {
    private JSlider slider;
    private int row;
    private int cloumn;
    private int scaledSliderValue;
    private String calculatedValue;


    public HexColorCalculator(JSlider s){
        this.slider = s;
    }

    public String CalculateHexColor(){
        int Max = this.slider.getMaximum();
        int Min = this.slider.getMinimum();
        int IntHexColor = this.slider.getValue();


        float FloatHexColor = IntHexColor;
        float span = Max-Min;
        float scale = span/(6*255);
        FloatHexColor = FloatHexColor/scale;
        System.out.print(FloatHexColor+"   ");
        IntHexColor = (int)FloatHexColor;
        System.out.println(IntHexColor);

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
        this.row = row;
        this.cloumn = column;
        this.scaledSliderValue = IntHexColor;
        this.calculatedValue = CalculatedValue;
        return String.valueOf(sb);
    }

    public int getRow(){
        return this.row;
    }

    public int getCloumn(){
        return this.cloumn;
    }

    public int getScaledSliderValue(){
        return this.scaledSliderValue;
    }

    public String getCalculatedValue(){
        return this.calculatedValue;
    }
}
