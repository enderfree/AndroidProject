package team3.samuelandsebastian.androidproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import team3.samuelandsebastian.androidproject.R;
import team3.samuelandsebastian.androidproject.models.DataModel;
import team3.samuelandsebastian.androidproject.models.Result;

public class ListWordAdapter extends ArrayAdapter<Result> {

    private int currentSelectedCell = -1;
    private DataModel model;
    private Context context;

    public ListWordAdapter(@NonNull Context context, DataModel dataModel, int currentSelectedCell) {
        super(context, -1, dataModel.getResults().toArray(new Result[0]));
        this.model = dataModel;
        this.context = context;
        this.currentSelectedCell = currentSelectedCell;
    }

    public int getCurrentSelectedCell() {
        return currentSelectedCell;
    }

    public void setCurrentSelectedCell(int currentSelectedCell) {
        this.currentSelectedCell = currentSelectedCell;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // inflating grid_item inside our view for this cell at position "i"


        if (view == null) {
            if(currentSelectedCell == position) {
                view = inflater.inflate(R.layout.cell_item_info, null);
                TextView txtViewDefinition, txtViewPart, txtViewWord, txtViewSynonyms, txtViewExamples;
                Result result = model.getResults().get(position);
                txtViewDefinition = view.findViewById(R.id.txtViewDefinition);
                txtViewPart = view.findViewById(R.id.txtViewPart);
                txtViewWord = view.findViewById(R.id.txtViewWord);
                txtViewSynonyms = view.findViewById(R.id.txtViewSynonyms);
                txtViewExamples = view.findViewById(R.id.txtViewExamples);

                StringBuilder sb = new StringBuilder();
                txtViewWord.setText((1 + position) + ". " + model.getWord());
                txtViewPart.setText(result.getPartOfSpeech());
                txtViewDefinition.setText("Definition: " + result.getDefinition());

                if(result.getSynonyms() != null) {
                    sb.append("Synonyms: ");
                    for(int i = 0; i < result.getSynonyms().size(); i++) {
                        if(i == 0)
                            sb.append(result.getSynonyms().get(i));
                        else
                            sb.append(", ").append(result.getSynonyms().get(i));
                    }
                    txtViewSynonyms.setText(sb.toString());
                }

                sb.setLength(0);

                if(result.getExamples() != null) {
                    for(int i = 0; i < result.getExamples().size(); i++) {
                        sb.append("Example ").append(i + 1).append(": ").append(result.getExamples().get(i)).append("\n");
                    }
                    txtViewExamples.setText(sb.toString());
                } else {
                    txtViewExamples.setText("");
                }

                sb.setLength(0);

            } else {
                view = inflater.inflate(R.layout.cell_item, null);
                TextView speech, word;
                speech = view.findViewById(R.id.txtViewPartOfSpeech);
                word = view.findViewById(R.id.txtViewWordCell);

                speech.setText(model.getResults().get(position).getPartOfSpeech());
                word.setText((1 +position) + ". " + model.getWord());
            }
        }
        return view;
    }


}
