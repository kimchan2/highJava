package kr.or.ddit.basic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class Controller {

	@FXML TextField txtName;
	@FXML RadioButton rbtMale;
	@FXML ToggleGroup grouprbt;
	@FXML RadioButton rbtFemale;
	@FXML CheckBox chktravel;
	@FXML CheckBox chkhiking;
	@FXML CheckBox chkReadBook;
	@FXML CheckBox chkBaduk;
	@FXML CheckBox chkJanggi;
	@FXML CheckBox chkGame;
	@FXML CheckBox chkTennis;
	@FXML CheckBox chkBadminton;
	@FXML Button btnShow;
	@FXML TextArea txtArea;
	
	@FXML public void btnShowClicked(MouseEvent event) {
		String str = "";
		
		CheckBox[] cBox = {chktravel, chkhiking, chkReadBook, chkBaduk, chkJanggi,
				chkGame, chkTennis, chkBadminton};
		
		str += "이름은 " + txtName.getText() + "입니다.\n";
		String[] temp = grouprbt.getSelectedToggle().toString().split("'");
		str += "성별은 " + temp[1] + "입니다.\n";
		
		str += "취미는 ";
		for(CheckBox cb : cBox) {
			if(cb.isSelected()) {
				str += cb.getText() + " ";
			}
		}
		str += "입니다.\n";
		
		txtArea.setText(str);
	}

}
