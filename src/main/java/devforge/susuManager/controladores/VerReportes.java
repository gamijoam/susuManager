package devforge.susuManager.controladores;

import devforge.susuManager.DTOs.ReporteDTO;
import devforge.susuManager.DTOs.SusuDTO2;
import devforge.susuManager.model.Pagos;
import devforge.susuManager.model.Reporte;
import devforge.susuManager.model.Susus;
import devforge.susuManager.model.Usuario;
import devforge.susuManager.servicios.PagosServicios;
import devforge.susuManager.servicios.SususServicios;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class VerReportes {
    @Autowired
    private PagosServicios pagoService; // Servicio para acceder a los pagos

    @Autowired
    private SususServicios susuService; // Servicio para acceder a los susus

    // Componentes de la interfaz gráfica
    @FXML
    private ComboBox<String> reportTypeComboBox; // Selector de tipo de reporte
    @FXML
    private ComboBox<SusuDTO2> susuComboBox; // Selector de susu
    @FXML
    private DatePicker startDatePicker; // Selector de fecha de inicio
    @FXML
    private DatePicker endDatePicker; // Selector de fecha de fin
    @FXML
    private TableView<ReporteDTO> reportTable; // Tabla para mostrar resultados
    @FXML
    private TableColumn<ReporteDTO, Long> idColumn; // Columna ID
    @FXML
    private TableColumn<ReporteDTO, String> descriptionColumn; // Columna Descripción
    @FXML
    private TableColumn<ReporteDTO, Double> amountColumn; // Columna Monto
    @FXML
    private TableColumn<ReporteDTO, LocalDate> dateColumn; // Columna Fecha

    private ObservableList<ReporteDTO> reportList = FXCollections.observableArrayList(); // Lista observable para los resultados

    @FXML
    public void initialize() {
        // Configurar columnas de la tabla
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("monto"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        // Cargar susus en el ComboBox
        susuComboBox.setItems(FXCollections.observableArrayList(susuService.listaSusus()));

        // Configurar tipos de reporte en el ComboBox
        reportTypeComboBox.setItems(FXCollections.observableArrayList(
                "Estado Financiero", "Pagos Pendientes", "Historial de Distribución"
        ));
    }
    @FXML
    private void generateReport() {
        // Limpiar resultados anteriores
        reportList.clear();

        // Obtener valores de los filtros
        String reportType = reportTypeComboBox.getValue(); // Tipo de reporte seleccionado
        SusuDTO2 selectedSusuDTO = susuComboBox.getValue(); // Susu seleccionado
        LocalDate startDate = startDatePicker.getValue(); // Fecha de inicio
        LocalDate endDate = endDatePicker.getValue(); // Fecha de fin

        // Validar que se haya seleccionado un tipo de reporte
        if (reportType == null || selectedSusuDTO == null) {
            showAlert("Error", "Debes seleccionar un tipo de reporte y un susu.");
            return;
        }

        // Convertir el DTO de susu a entidad (si es necesario)
        Susus selectedSusu = new Susus();
        selectedSusu.setIdSusu(selectedSusuDTO.getIdSusu());

        // Generar el reporte según el tipo seleccionado
        switch (reportType) {
            case "Estado Financiero":
                reportList.addAll(pagoService.getEstadoFinanciero(selectedSusu, startDate, endDate));
                break;
            case "Pagos Pendientes":
                reportList.addAll(pagoService.getPagosPendientes(selectedSusu, startDate, endDate));
                break;
            case "Historial de Distribución":
                reportList.addAll(pagoService.getHistorialDistribucion(selectedSusu, startDate, endDate));
                break;
            default:
                showAlert("Error", "Tipo de reporte no válido.");
                return;
        }

        // Actualizar la tabla con los resultados
        reportTable.setItems(reportList);
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
