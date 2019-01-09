package projektporun;

import model.dao.*;
import domain.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DataBaseMenuController implements Initializable {

    FXMLConnectionWithDataBaseController fXMLConnectionWithDataBaseController = new FXMLConnectionWithDataBaseController();

    @FXML
    private TableColumn<Marks, String> col_Marks_subject_name;
    @FXML
    private Text wykonanoDodanie;
    @FXML
    private TextField textFieldStudentName, subjectField, TextFieldStudentUserName;
    @FXML
    private ChoiceBox setGroup, setMark;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TableColumn<Student, String> col_imie;
    @FXML
    private TableColumn<Student, String> col_nazwisko;
    @FXML
    private Button addButton, updateStudent, groupUpdate, updateMark, deleteStudent, deleteGroup, deletePrzedmiot;
    @FXML
    private TableView<Student> TableViewStudent;
    @FXML
    private TableColumn<Group, String> group_name;
    @FXML
    private TableColumn<Marks, Integer> col_mark;
    @FXML
    private TableColumn<Subject, String> col_subject_name;
    @FXML
    private TableView<Group> TableViewGroup;
    @FXML
    private TableView<Marks> TableViewMarks;
    @FXML
    private TableView<Subject> TableViewSubject;
    @FXML
    private TextField searchingField;
    @FXML
    private TextField searchingSubjectField;

    private Set<Marks> marks;
    private final StudentDAO studentDAO = new StudentDAO();
    private final SubjectDAO subjectDAO = new SubjectDAO();
    private final MarksDAO marksDAO = new MarksDAO();
    private final GroupDAO groupDAO = new GroupDAO();
    FilteredList<Student> wyszukiwanieStudenta = new FilteredList(FXCollections.observableArrayList(studentDAO.getAll()), p -> true);
    FilteredList<Subject> wyszukiwaniePrzedmiotu = new FilteredList(FXCollections.observableArrayList(subjectDAO.getAll()), p -> true);
    FilteredList<Marks> wyszukiwanieOceny = new FilteredList(FXCollections.observableArrayList(marksDAO.getAll()), p -> true);
    ObservableList<Integer> markList = FXCollections.observableArrayList(1, 2, 3, 4, 5);
    ObservableList<String> groupList = FXCollections.observableArrayList("Laboratorium 1", "Laboratorium 2", "Laboratorium 3");

    @FXML
    private Button updateSubject;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        wykonanoDodanie.setVisible(false);
        wklejanieOceny();
        wklejaniePrzedmiotu();
        wklejanieGrupy();
        wklejanieStudenta();
        setGroup.setItems(groupList);
        setMark.setItems(markList);
        //Dodajemy studenta wraz z grupą laboratoryjną,oceną oraz przedmiotem
        addButton.setOnAction((ActionEvent event) -> {
            if (textFieldStudentName.getText() != null && !TextFieldStudentUserName.getText().isEmpty()) {
                try {

                    int selectedMark = setMark.getSelectionModel().getSelectedIndex();
                    selectedMark++;
                    String selectedGroup = (String) setGroup.getSelectionModel().getSelectedItem();

                    //Tworzymy obiekty
                    Student student = new Student();
                    Subject subject = new Subject();
                    Marks mark = new Marks();
                    Group group = new Group();
                    //Nadajemy parametry
                    group.setGroup_name(selectedGroup);
                    mark.setMark(selectedMark);
                    subject.setSubject_name(subjectField.getText());
                    student.setName(textFieldStudentName.getText());
                    student.setUserName(TextFieldStudentUserName.getText());
                    mark.setStudent(student);
                    mark.setSubject(subject);
                    student.setGroup(group);

                    //
                    subjectDAO.create(subject);
                    groupDAO.create(group);
                    studentDAO.create(student);
                    marksDAO.create(mark);

                    TableViewStudent.setItems(FXCollections.observableArrayList(studentDAO.getAll()));
                    TableViewGroup.setItems(FXCollections.observableList(groupDAO.getAll()));
                    TableViewMarks.setItems(FXCollections.observableArrayList(marksDAO.getAll()));
                    TableViewSubject.setItems(FXCollections.observableArrayList(subjectDAO.getAll()));

                    textFieldStudentName.clear();
                    TextFieldStudentUserName.clear();
                    subjectField.clear();
                    wykonanoDodanie.setVisible(true);
                } catch (Exception exc) {

                }
            }
        });

        deleteStudent.setOnAction((ActionEvent event) -> {
            if (TableViewStudent.getSelectionModel().getSelectedItem() != null) {
                Student student = new Student();
                Marks marks = new Marks();
                Subject subject = new Subject();
                student = TableViewStudent.getSelectionModel().getSelectedItem();
                studentDAO.delete(student);
                marksDAO.delete(marks);
                subjectDAO.delete(subject);

                TableViewStudent.setItems(FXCollections.observableList(studentDAO.getAll()));
                TableViewMarks.setItems(FXCollections.observableList(marksDAO.getAll()));
                TableViewSubject.setItems(FXCollections.observableList(subjectDAO.getAll()));

            }
        });

        deleteGroup.setOnAction((ActionEvent event) -> {
            if (TableViewGroup.getSelectionModel().getSelectedItem() != null) {
                Group group = new Group();
                group = TableViewGroup.getSelectionModel().getSelectedItem();
                groupDAO.delete(group);

                TableViewGroup.setItems(FXCollections.observableList(groupDAO.getAll()));

            }
        });

        //        deletePrzedmiot.setOnAction((ActionEvent event) -> {
        //            if (TableViewPrzedmiot.getSelectionModel().getSelectedItem() != null) {
        //                Przedmiot przedmiot=new Przedmiot();
        //                przedmiot = TableViewPrzedmiot.getSelectionModel().getSelectedItem();
        //                przedmiotDAO.delete(przedmiot);
        //
        //                TableViewPrzedmiot.setItems(FXCollections.observableList(przedmiotDAO.getAll()));
        //
        //            }
        //        });
        searchingField.setOnKeyReleased(keyEvent -> {
            wyszukiwanieStudenta.setPredicate(p -> p.getName().toLowerCase().contains(searchingField.getText().toLowerCase()));

            TableViewStudent.setItems(wyszukiwanieStudenta);

        });
        searchingSubjectField.setOnKeyReleased(keyEvent -> {
            wyszukiwaniePrzedmiotu.setPredicate(p -> p.getSubject_name().toLowerCase().contains(searchingSubjectField.getText().toLowerCase()));
            TableViewSubject.setItems(wyszukiwaniePrzedmiotu);
        });

        updateStudent.setOnAction((ActionEvent event) -> {
            if (textFieldStudentName.getText() != null && !TextFieldStudentUserName.getText().isEmpty() && TableViewStudent.getSelectionModel().getSelectedItem() != null) {

                Student student = new Student();
                student = TableViewStudent.getSelectionModel().getSelectedItem();

                student.setName(textFieldStudentName.getText());
                student.setUserName(TextFieldStudentUserName.getText());
                studentDAO.update(student);

                TableViewStudent.setItems(FXCollections.observableList(studentDAO.getAll()));
                TableViewGroup.setItems(FXCollections.observableArrayList(groupDAO.getAll()));
                textFieldStudentName.clear();
                TextFieldStudentUserName.clear();
            }
        });

        groupUpdate.setOnAction((ActionEvent event) -> {
            if (TableViewGroup.getSelectionModel().getSelectedItem() != null) {
                String selectedGroup = (String) setGroup.getSelectionModel().getSelectedItem();

                Group group = new Group();
                group = TableViewGroup.getSelectionModel().getSelectedItem();

                group.setGroup_name(selectedGroup);
                groupDAO.update(group);

                TableViewGroup.setItems(FXCollections.observableArrayList(groupDAO.getAll()));
            }
        });

        updateMark.setOnAction((ActionEvent event) -> {
            if (TableViewMarks.getSelectionModel().getSelectedItem() != null) {
                int selectedMark = setMark.getSelectionModel().getSelectedIndex();
                selectedMark++;
                Marks mark = new Marks();
                mark = TableViewMarks.getSelectionModel().getSelectedItem();

                mark.setMark(selectedMark);
                marksDAO.update(mark);

                TableViewMarks.setItems(FXCollections.observableArrayList(marksDAO.getAll()));
            }
        });

        updateSubject.setOnAction((ActionEvent event) -> {
            if (TableViewSubject.getSelectionModel().getSelectedItem() != null && subjectField.getText() != null) {

                Subject subject = new Subject();
                subject = TableViewSubject.getSelectionModel().getSelectedItem();

                subject.setSubject_name(subjectField.getText());
                subjectDAO.update(subject);

                TableViewSubject.setItems(FXCollections.observableArrayList(subjectDAO.getAll()));
                subjectField.clear();
            }
        });
    }

    private void wklejanieStudenta() {
        col_imie.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_nazwisko.setCellValueFactory(new PropertyValueFactory<>("UserName"));
        TableViewStudent.setItems(FXCollections.observableArrayList(studentDAO.getAll()));
    }

    private void wklejanieGrupy() {
        group_name.setCellValueFactory(new PropertyValueFactory<>("group_name"));
        TableViewGroup.setItems(FXCollections.observableArrayList(groupDAO.getAll()));
    }

    private void wklejanieOceny() {
        col_mark.setCellValueFactory(new PropertyValueFactory<>("mark"));
        col_Marks_subject_name.setCellValueFactory(new PropertyValueFactory<>(col_subject_name.toString()));
        TableViewMarks.setItems(FXCollections.observableArrayList(marksDAO.getAll()));

    }

    private void wklejaniePrzedmiotu() {
        col_subject_name.setCellValueFactory(new PropertyValueFactory<>("subject_name"));
        TableViewSubject.setItems(FXCollections.observableArrayList(subjectDAO.getAll()));
    }
}
