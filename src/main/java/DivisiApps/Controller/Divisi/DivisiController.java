package DivisiApps.Controller.Divisi;

import DivisiApps.DAO.DAODivisi;
import DivisiApps.Main;
import DivisiApps.Model.DivisiModel;
import DivisiApps.Utilities.AlertUtil;
import DivisiApps.Utilities.Contracts.ContractConfirmation;
import DivisiApps.Utilities.Contracts.ContractSumbitForm;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class DivisiController {
    @FXML
    private TextField searchText;

    @FXML
    private TableColumn<DivisiModel, String> kodeDivisiColumn;

    @FXML
    private TableColumn<DivisiModel, String> namaDivisiColumn;

    @FXML
    private TableColumn<DivisiModel, String> kodeKantorColumn;

    @FXML
    private TableView divisiTableView;

    @FXML
    private Button btnEdit;

    private Executor exec;

    @FXML
    private void initialize() {
        exec = Executors.newCachedThreadPool(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                return t;
            }
        });

        /*FontAwesomeIconView bla = new FontAwesomeIconView();
        bla.setStyleClass("thumb-up-icon");*/
        //btnEdit.setStyle("thumb-up-icon;");

        kodeDivisiColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DivisiModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DivisiModel, String> param) {
                return param.getValue().kodeDivisiProperty();
            }
        });

        namaDivisiColumn.setCellValueFactory(param -> param.getValue().divisiProperty());
        kodeKantorColumn.setCellValueFactory(param -> param.getValue().kodeKantorProperty());

        divisiTableView.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                DivisiModel divisiModel = (DivisiModel) divisiTableView.getSelectionModel().getSelectedItem();
                editDivisi(divisiModel);
            }
        });

        getListDivisi();
    }

    @FXML
    private void getListDivisi() {
        try {
            try {
                ObservableList<DivisiModel> divisiModelObservableList = DAODivisi.getListDivisi();
                populateListDivisi(divisiModelObservableList);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void populateListDivisi(ObservableList<DivisiModel> divisiModel) throws ClassNotFoundException {
        divisiTableView.setItems(divisiModel);
    }

    @FXML
    private void searchDivisi() {
        try {
            try {
                ObservableList<DivisiModel> divisiModelObservableList = DAODivisi.searchDivisi(searchText.getText());
                populateListDivisi(divisiModelObservableList);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void editDivisi(DivisiModel divisiModel) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/layout/divisi/layout_edit.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit divisi");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(Main.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);

            EditDivisiController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setDivisiModel(divisiModel);
            dialogStage.setOnHidden(event -> {
                getListDivisi();
            });
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnEdit() {
        DivisiModel divisiModel = (DivisiModel) divisiTableView.getSelectionModel().getSelectedItem();
        if (divisiModel == null) {
            AlertUtil.show(Alert.AlertType.ERROR, "Error", "Oops! Terjadi kesalahan", "Tidak ada item yang terpilih", null);
        } else {
            editDivisi(divisiModel);
        }
    }

    @FXML
    private void btnTambahDivisi() {
        try {
            Stage stage = new Stage();
            stage.setTitle("Tambah Divisi");

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("/layout/divisi/layout_create.fxml"));
            Pane pane = (Pane) fxmlLoader.load();

            CreateDivisiController controller = (CreateDivisiController) fxmlLoader.getController();
            controller.setDialogStage(stage);

            stage.setScene(new Scene(pane));
            stage.initOwner(Main.getPrimaryStage());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setResizable(false);
            stage.setOnHidden(event -> {
                getListDivisi();
            });
            System.out.println(String.format("%4.2f", 31.67));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnDelete() {
        DivisiModel divisiModel = (DivisiModel) divisiTableView.getSelectionModel().getSelectedItem();

        if (divisiModel == null) {
            AlertUtil.show(Alert.AlertType.ERROR, "Error", "Oops! Terjadi kesalahan", "Tidak ada item yang terpilih", null);
        } else {
            String kodeDivisi = divisiModel.getKodeDivisi();
            String namaDivisi = divisiModel.getDivisi();
            AlertUtil.show(Alert.AlertType.CONFIRMATION,
                    "Confirmation",
                    "Confirmation Message",
                    "Apakah anda ingin hapus " + namaDivisi + " ?", new ContractConfirmation() {
                        @Override
                        public void btnOK(Alert alert) {
                            try {
                                DAODivisi.deleteDivisi(kodeDivisi, new ContractSumbitForm<SQLException>() {
                                    @Override
                                    public void onSuccess() {
                                        AlertUtil.show(Alert.AlertType.INFORMATION,
                                                "Information",
                                                "Success",
                                                namaDivisi + " berhasil dihapus",
                                                null);
                                        getListDivisi();

                                    }

                                    @Override
                                    public void onError(SQLException e) {
                                        AlertUtil.show(Alert.AlertType.ERROR,
                                                "Error",
                                                "Gagal",
                                                namaDivisi + " gagal dihapus",
                                                null);
                                    }
                                });
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void btnCancel(Alert alert) {
                            alert.close();
                        }
                    });
        }
    }
}
