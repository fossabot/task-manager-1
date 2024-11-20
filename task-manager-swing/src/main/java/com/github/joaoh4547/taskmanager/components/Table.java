package com.github.joaoh4547.taskmanager.components;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.github.joaoh4547.taskmanager.utils.Objects;

public class Table<T>
  extends JPanel {

  private final JTable table;
  private final GenericTableModel<T> model;

  // private final DefaultTableModel model = new DefaultTableModel();
  private Collection<T> tableData = new ArrayList<T>();

  private final Collection<TableColumn> columns = new ArrayList<>();

  public Table() {
    setLayout(new BorderLayout());
    model = new GenericTableModel<T>() {
      @Override
      public int getColumnCount() {
        return columns.size();
      }

      @Override
      public Object getValueAt(int rowIndex, int columnIndex) {
        return toObjectArray()[rowIndex][columnIndex];
      }
    };

    table = new JTable(model);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    ajustarLarguraColunas(table);

    // setPreferredScrollableViewportSize(new Dimension(500, 100));
    // setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
  }

  private void ajustarLarguraColunas(JTable tabela) {
    for (int coluna = 0; coluna < tabela.getColumnCount(); coluna++) {
      javax.swing.table.TableColumn tableColumn = tabela.getColumnModel()
          .getColumn(coluna);
      int larguraPreferida = 75; // Largura mínima
      int larguraMaxima = 300; // Largura máxima

      // Determinando o tamanho ideal com base nas células
      for (int linha = 0; linha < tabela.getRowCount(); linha++) {
        Component componente = tabela
            .prepareRenderer(tabela.getCellRenderer(linha, coluna), linha,
                             coluna);
        larguraPreferida = Math.max(componente.getPreferredSize().width,
                                    larguraPreferida);
      }

      larguraPreferida = Math.min(larguraPreferida, larguraMaxima);
      tableColumn.setPreferredWidth(larguraPreferida);
    }
  }

  public void addColumn(String property) {
    columns.add(new TableColumn(property, property));
  }

  public void addColumn(String property, String name) {
    columns.add(new TableColumn(property, name));
  }

  public void setTableData(Collection<T> tableData) {
    this.tableData = tableData;
    refreshData();
  }

  private String[] getColumns() {
    return columns.stream().map(TableColumn::name).toArray(String[]::new);
  }

  private Object[][] toObjectArray() {
    Object[][] data = new Object[tableData.size()][columns.size()];
    int i = 0;
    for (T rowData : tableData) {
      int j = 0;
      for (TableColumn column : columns) {
        data[i][j] = getPropertyFromRowData(rowData, column.property());
        j++;
      }
      i++;
    }
    return data;
  }

  public void refreshData() {
    // String[] colunas = { "ID", "Nome", "Idade" };
    // Object[][] novosDados = { { 1, "João", 25 }, { 2, "Maria", 30 },
    // { 3, "José", 22 } };
    // model.setDataVector(novosDados, colunas);
    // autosize();

    model.getItens().clear();
    for (T md : model.getItemsAsList()) {
      model.removeItem(model.getItemsAsList().indexOf(md));
    }
    model.getItens().clear();

    for (T data : tableData) {
      model.addItem(data);
    }

    model.fireTableDataChanged();
  }

  private Object getPropertyFromRowData(T rowData, String property) {
    return Objects.getValue(rowData, property);
  }

  // public void autosize() {
  // for (int coluna = 0; coluna < getColumnCount(); coluna++) {
  // javax.swing.table.TableColumn tableColumn = getColumnModel()
  // .getColumn(coluna);
  // int larguraPreferida = 50; // Largura mínima
  // int larguraMaxima = 300; // Limite para largura máxima
  //
  // // Determinando o tamanho com base no conteúdo das células
  // for (int linha = 0; linha < getRowCount(); linha++) {
  // Component componente = prepareRenderer(getCellRenderer(linha,
  // coluna),
  // linha, coluna);
  // larguraPreferida = Math.max(componente.getPreferredSize().width,
  // larguraPreferida);
  // }
  //
  // // Aplicando limites
  // larguraPreferida = Math.min(larguraPreferida, larguraMaxima);
  //
  // // Configurando a largura final da coluna
  // tableColumn.setPreferredWidth(larguraPreferida);
  // }
  // }

}
