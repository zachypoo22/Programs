package views;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JSpinner;

/**
 *
 * @author rkline
 */
public class FigureFrame extends javax.swing.JFrame {
  public Canvas getCanvas() {
    return (Canvas) canvas;
  }
  public JList getFigureList() {
    return figureList;
  }
  public JSpinner getScaleSpinner() {
    return scale;
  }
  public JMenuItem getLoadSamples() {
    return loadSamples;
  }
  public JMenuItem getAddRectDialog() {
    return addRectDialog;
  }
  public JButton getRemoveBtn() {
      return removeBtn;
  }
  public JButton getMoveBtn() {
      return moveBtn;
  }
  public JMenuItem getAddCircleDialog()
  {
      return addCircleItem;
  }

  /**
   * Creates new form FigureFrame
   */
  public FigureFrame() {
    initComponents();
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        java.awt.GridBagConstraints gridBagConstraints;

        scalePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        scale = new javax.swing.JSpinner();
        listPane = new javax.swing.JScrollPane();
        figureList = new javax.swing.JList<>();
        canvasPane = new javax.swing.JScrollPane();
        canvas = new Canvas();
        buttonPanel = new javax.swing.JPanel();
        moveBtn = new javax.swing.JButton();
        removeBtn = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        loadSamples = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        addRectDialog = new javax.swing.JMenuItem();
        addCircleItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("scale:");
        scalePanel.add(jLabel1);
        scalePanel.add(scale);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        getContentPane().add(scalePanel, gridBagConstraints);

        figureList.setModel(new javax.swing.AbstractListModel<String>()
        {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listPane.setViewportView(figureList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 4, 2);
        getContentPane().add(listPane, gridBagConstraints);

        canvas.setEditable(false);
        canvas.setColumns(20);
        canvas.setRows(5);
        canvasPane.setViewportView(canvas);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(4, 2, 4, 4);
        getContentPane().add(canvasPane, gridBagConstraints);

        buttonPanel.setLayout(new javax.swing.BoxLayout(buttonPanel, javax.swing.BoxLayout.Y_AXIS));

        moveBtn.setText("Move to Top");
        buttonPanel.add(moveBtn);

        removeBtn.setText("Remove");
        buttonPanel.add(removeBtn);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        getContentPane().add(buttonPanel, gridBagConstraints);

        jMenu1.setText("File");

        loadSamples.setText("Load Samples");
        jMenu1.add(loadSamples);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Figures");

        addRectDialog.setText("Add Rectangle");
        addRectDialog.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                addRectDialogActionPerformed(evt);
            }
        });
        jMenu2.add(addRectDialog);

        addCircleItem.setText("Add Circle");
        jMenu2.add(addCircleItem);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

  private void addRectDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRectDialogActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_addRectDialogActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem addCircleItem;
    private javax.swing.JMenuItem addRectDialog;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JTextArea canvas;
    private javax.swing.JScrollPane canvasPane;
    private javax.swing.JList<String> figureList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane listPane;
    private javax.swing.JMenuItem loadSamples;
    private javax.swing.JButton moveBtn;
    private javax.swing.JButton removeBtn;
    private javax.swing.JSpinner scale;
    private javax.swing.JPanel scalePanel;
    // End of variables declaration//GEN-END:variables
}
