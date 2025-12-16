package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

public class SampleController {
	@FXML
	private Label message;
	
	@FXML
	private ComboBox<String> tableSelect;
	
	@FXML
	private TextField nbrLine;
	
	@FXML
	private TableView<Attribut> view;
	
	@FXML
	private TableColumn<Attribut,String> viewColTable;
	@FXML
	private TableColumn<Attribut,String> viewColType;
	@FXML
	private TableColumn<Attribut,String> viewColData;
	
	public void initialize() {
		// force the field to be numeric only
		nbrLine.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		            nbrLine.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		});
	}
	
	public void initTableSelect() {
		if(tableSelect.getItems().isEmpty()) {
			tableSelect.setItems(Table.getAllNames());
		}
	}
	public void setView() {
		for (int i = 0 ; i<Table.tables.size();i++) { //cycle through tables
			if (Table.tables.get(i).getName()==tableSelect.getValue()) { //if table selected found load attributes in table view
				ObservableList<Attribut> list = FXCollections.observableArrayList(Table.tables.get(i).getAttrib());
				view.setItems(list);

				viewColTable.setCellValueFactory(new PropertyValueFactory<>("name"));
				viewColType.setCellValueFactory(new PropertyValueFactory<>("sqlType"));
				
				// Create a new cell factory for the ComboBoxTableCell
	            viewColData.setCellFactory(tc -> new ComboBoxTableCell<Attribut, String>() {
	            	@Override
	                public void updateItem(String item, boolean empty) {
	                    super.updateItem(item, empty);
	                    if (!isEmpty()) {
	                        Attribut attribut = getTableRow().getItem();
	                        ComboBox<String> comboBox = new ComboBox<>(getOptionsForType(attribut.getSqlType()));
	                        comboBox.setValue(item);
	                        setGraphic(comboBox);

	                        // Add an event handler to the ComboBox
	                        comboBox.setOnAction(event -> {
	                            String selectedValue = comboBox.getValue();
	                            for(Table table : Table.tables) {
	                            	if (table.getName().equals(tableSelect.getValue())) {
	                            		Table.tables.get(Table.tables.indexOf(table)).getAttrib().get(table.getAttrib().indexOf(attribut)).setType(selectedValue);
	                            	}
	                            }
	                        });
	                    }
	                }
	            });
				break;
			}
		}
	}
	private ObservableList<String> getOptionsForType(String sqlType) {
	    ObservableList<String> options = FXCollections.observableArrayList();
	    
	    switch(sqlType.toUpperCase()) {
	    case "PRIMARY KEY" :
	    	options.addAll("pk");
	    	break;
	    case "FOREIGN KEY" :
	    	options.addAll("fk");
	    	break;
	    case "VARCHAR" :
		case "VARCHAR(50)":
			options.addAll("Last name","First name","Address","City","Phone number","Random int","Brand","Model");
	    	break;
		case "DATE" :
			options.addAll("past date","upcom date");
	    	break;
		case "INT" :
			options.addAll("1digit","2digit");
	    	break;
		case "FLOAT" :
			options.addAll("2digit2","2digit3");
	    	break;
		case "DOUBLE" :
			options.addAll("2.2digit","2.3digit");
	    	break;
		default :
			options.addAll("Undefined");
	    }
	    
	    // Add options based on sqlType
	    /*if (sqlType.equals("INT")) {
	        options.addAll("1", "2", "3");
	    } else if (sqlType.equals("Varchar")) {
	        options.addAll("A", "B", "C");
	    }*/

	    return options;
	}
	public void genInsert() {
		boolean stop=false;
		for(Table table : Table.tables) {
			for(Attribut attrib : table.getAttrib()) {
				if(attrib.getType().isEmpty()) {
					stop=true;
				}
			}
		}
		if(stop) {
			message.setText("Please, select every data type on every table before generate");
		} else {
			Table.setNbrOfLine(Integer.valueOf(nbrLine.getText()));
			InsertGenerator.sqlGenerator();
			message.setText("Insert generation successful");
		}
	}
}
