package GUI;

import Controller.AlunoController;
import Controller.CursoAlunoController;
import Controller.CursoController;
import Model.Aluno;
import Model.Curso;
import Model.CursoAluno;
import Model.TableCellRender;
import Model.TableModel;
import java.awt.Color;
import javax.swing.JOptionPane;

public class ConsultaClasse extends javax.swing.JInternalFrame {

    CursoAlunoController controller = null;
    AlunoController alunoController = null;
    CursoController cursoController = null;
    TableModel tabela = null;
    CursoAluno cursoAluno;
    Curso curso;
    Aluno aluno;

    public ConsultaClasse() {
        initComponents();
        aluno = new Aluno();
        curso = new Curso();
        controller = new CursoAlunoController();
        cursoController = new CursoController();
        alunoController = new AlunoController();
    }

    private void carregaTabela() {
        tabela.addColumn("Cod. Classe");
        tabela.addColumn("Aluno");
        tabela.addColumn("Curso");
        tblCursoAluno.setGridColor(Color.WHITE);
        tblCursoAluno.setRowHeight(25);
        tblCursoAluno.setDefaultRenderer(Integer.class, new TableCellRender());
        tblCursoAluno.setDefaultRenderer(Object.class, new TableCellRender());
    }

    public void consultar() {
        cursoAluno = new CursoAluno();
        tabela = new TableModel();
        carregaTabela();

        if (!txtCodigo.getText().isEmpty()) {
            cursoAluno.setCodigo(Integer.parseInt(txtCodigo.getText()));
        }
        cursoAluno.setAluno(aluno);
        cursoAluno.setCurso(curso);

        for (CursoAluno c : controller.get(cursoAluno)) {
            tabela.addRow(new Object[]{
                c.getCodigo(),
                c.getAluno().getCodigo() + " - " + c.getAluno().getNome(),
                c.getCurso().getCodigo() + " - " + c.getCurso().getDescricao()
            });
        }
        tblCursoAluno.setModel(tabela);
    }

    public void editar() {
        cursoAluno = new CursoAluno();
        int linhaSelecionada = tblCursoAluno.getSelectedRow();

        if (linhaSelecionada >= 0) {
            cursoAluno.setCodigo(Integer.parseInt(tblCursoAluno.getValueAt(linhaSelecionada, 0).toString()));
            cursoAluno = controller.buscaPorCodigo(cursoAluno);
            GerenciaClasse classe = new GerenciaClasse(cursoAluno);
            this.getParent().add(classe);
            classe.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum Registro Selecionado!");
        }
    }

    public void excluir() {
        cursoAluno = new CursoAluno();
        int linhaSelecionada = tblCursoAluno.getSelectedRow();

        if (linhaSelecionada >= 0) {
            cursoAluno.setCodigo(Integer.parseInt(tblCursoAluno.getValueAt(linhaSelecionada, 0).toString()));
            if (controller.delete(cursoAluno)) {
                JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
                tabela.removeRow(linhaSelecionada);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum registro selecionado!");
        }
    }

    public void incluir() {
        GerenciaClasse classe = new GerenciaClasse();
        this.getParent().add(classe);
        classe.setVisible(true);
    }

    private void buscarAlunoPorCodigo() {
        if (!txtcodAluno.getText().isEmpty()) {
            aluno = new Aluno();
            aluno.setCodigo(Integer.parseInt(txtcodAluno.getText()));
            aluno = alunoController.buscarPorCodigo(aluno);
            if (aluno.getCodigo() <= 0) {
                JOptionPane.showMessageDialog(null, "Nao Encontrado!");
                aluno = null;
                txtcodAluno.setText("");
                txtcodAluno.requestFocus();
            }

        } else {
            aluno = null;
        }
    }

    private void buscarCursoPorCodigo() {
        if (!txtCodCurso.getText().isEmpty()) {
            curso = new Curso();
            curso.setCodigo(Integer.parseInt(txtCodCurso.getText()));
            curso = cursoController.buscarPorCodigo(curso);
            if (curso.getCodigo() <= 0) {
                JOptionPane.showMessageDialog(null, "Nao encontrado!");
                curso = null;
                txtCodCurso.setText("");
                txtCodCurso.requestFocus();
            }
        } else {
            curso = null;
        }
    }

    public void setAluno(Aluno a) {
        if (a != null) {
            this.aluno = a;
            txtcodAluno.setText(String.valueOf(aluno.getCodigo()));
        }
    }

    public void setCurso(Curso c) {
        if (c != null) {
            this.curso = c;
            txtCodCurso.setText(String.valueOf(curso.getCodigo()));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        txtcodAluno = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCodCurso = new javax.swing.JFormattedTextField();
        btnBuscarAluno = new javax.swing.JButton();
        btnBuscarCurso = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCursoAluno = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btnConsultar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        btnIncluir = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();

        setClosable(true);
        setTitle("Consulta de Classes");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Classe:");

        jLabel3.setText("Aluno:");

        txtcodAluno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtcodAlunoFocusLost(evt);
            }
        });

        jLabel4.setText("Curso:");

        txtCodCurso.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodCursoFocusLost(evt);
            }
        });

        btnBuscarAluno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/buscar.png"))); // NOI18N
        btnBuscarAluno.setToolTipText("Buscar");
        btnBuscarAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarAlunoActionPerformed(evt);
            }
        });

        btnBuscarCurso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/buscar.png"))); // NOI18N
        btnBuscarCurso.setToolTipText("Buscar");
        btnBuscarCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCursoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtcodAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtCodCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCodCurso)
                            .addComponent(btnBuscarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 25, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtcodAluno)
                            .addComponent(btnBuscarAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 25, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblCursoAluno.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblCursoAluno);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 890, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/buscar.png"))); // NOI18N
        btnConsultar.setText("Consultar");
        btnConsultar.setToolTipText("Consultar");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sair.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.setToolTipText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnConsultar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnConsultar)
                .addComponent(btnSair))
        );

        btnIncluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novo.png"))); // NOI18N
        btnIncluir.setToolTipText("Incluir");
        btnIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirActionPerformed(evt);
            }
        });

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/editar.png"))); // NOI18N
        btnEditar.setToolTipText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/excluir.png"))); // NOI18N
        btnExcluir.setToolTipText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        consultar();
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        editar();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        excluir();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirActionPerformed
        incluir();
    }//GEN-LAST:event_btnIncluirActionPerformed

    private void btnBuscarAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarAlunoActionPerformed
        ConsultaAluno frmAluno = new ConsultaAluno(this);
        getParent().add(frmAluno);
        frmAluno.setVisible(true);
    }//GEN-LAST:event_btnBuscarAlunoActionPerformed

    private void btnBuscarCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCursoActionPerformed
        ConsultaCurso frmCurso = new ConsultaCurso(this);
        getParent().add(frmCurso);
        frmCurso.setVisible(true);
    }//GEN-LAST:event_btnBuscarCursoActionPerformed

    private void txtcodAlunoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcodAlunoFocusLost
        buscarAlunoPorCodigo();
    }//GEN-LAST:event_txtcodAlunoFocusLost

    private void txtCodCursoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodCursoFocusLost
        buscarCursoPorCodigo();
    }//GEN-LAST:event_txtCodCursoFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarAluno;
    private javax.swing.JButton btnBuscarCurso;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnIncluir;
    private javax.swing.JButton btnSair;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCursoAluno;
    private javax.swing.JFormattedTextField txtCodCurso;
    private javax.swing.JFormattedTextField txtCodigo;
    private javax.swing.JFormattedTextField txtcodAluno;
    // End of variables declaration//GEN-END:variables
}
