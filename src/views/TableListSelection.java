package views;

import javax.swing.DefaultListSelectionModel;

//this class needed for selecting and unselecting multiple table elements without using ctrl key
public class TableListSelection extends DefaultListSelectionModel {
    @Override
    public void setSelectionInterval(int index1, int index2) {
        // Select multiple lines in else

        if (index1 == index2) {

            // unselect and select single line

            if (!isSelectedIndex(index1)) {
                addSelectionInterval(index1, index1);
            } else {

                removeSelectionInterval(index1, index1);
            }
        } else {
            addSelectionInterval(index1, index2);
        }
    }
}