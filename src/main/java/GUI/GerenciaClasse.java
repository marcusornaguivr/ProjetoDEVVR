package GUI;

import Controller.AlunoController;
import Controller.CursoAlunoController;
import Controller.CursoController;
import Model.Aluno;
import Model.Curso;
import Model.CursoAluno;
import javax.swing.JOptionPane;

/**
 *
 * @author gabri
 */
public class GerenciaClasse extends javax.swing.JInternalFrame {

    private final CursoAlunoController controller;
    private AlunoController alunoController;
    private CursoController cursoController;
    private CursoAluno cursoAluno;
    private Aluno aluno;
    private Curso curso;

    public GerenciaClasse() {
        initComponents();

        controller = new CursoAlunoController();
        aluno = new Aluno();
        curso = new Curso();
        cursoAluno = new CursoAluno();
        alunoController = new AlunoController();
        cursoController = new CursoController();
    }

    public GerenciaClasse(CursoAluno cursoAluno) {
        initComponents();

        this.cursoAluno = cursoAluno;
        controller = new CursoAlunoController();
        aluno = new Aluno();
        curso = new Curso();
        alunoController = new AlunoController();
        cursoController = new CursoController();

        txtCodAluno.setText(String.valueOf(this.cursoAluno.getAluno().getCodigo()));
        txtCodCurso.setText(String.valueOf(this.cursoAluno.getCurso().getCodigo()));
        txtDescAluno.setText(this.cursoAluno.getAluno().getNome());
        txtDescCurso.setText(this.cursoAluno.getCurso().getDescricao());
    }


    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
        txtCodAluno.setText(String.valueOf(this.aluno.getCodigo()));
        txtDescAluno.setText(this.aluno.getNome());
        cursoAluno.setAluno(this.aluno);
    }

    public void setCurso(Curso curso) {
        txtCodCurso.setText(String.valueOf(curso.getCodigo()));
        txtDescCurso.setText(curso.getDescricao());
        cursoAluno.setCurso(curso);
    }

    private void salvar() {
        if (!txtCodAluno.getText().isEmpty()) {
            aluno.setCodigo(Integer.parseInt(txtCodAluno.getText()));
        } else {
            JOptionPane.showMessageDialog(null, "Preencha os dados corretamente!");
            txtCodAluno.requestFocus();
            return;
        }

        if (!txtDescAluno.getText().isEmpty()) {
            aluno.setNome(txtDescAluno.getText());
        } else {
            JOptionPane.showMessageDialog(null, "Preencha os dados corretamente!");
            txtDescAluno.requestFocus();
            return;
        }

        if (!txtCodCurso.getText().isEmpty()) {
            curso.setCodigo(Integer.parseInt(txtCodCurso.getText()));
        } else {
            JOptionPane.showMessageDialog(null, "Preencha os dados corretamente!");
            txtCodCurso.requestFocus();
            return;
        }

        if (!txtDescCurso.getText().isEmpty()) {
            curso.setDescricao(txtDescCurso.getText());
        } else {
            JOptionPane.showMessageDialog(null, "Preencha os dados corretamente!");
            txtDescCurso.requestFocus();
            return;
        }

        cursoAluno.setAluno(aluno);
        cursoAluno.setCurso(curso);

        if (controller.inserir(cursoAluno) > 0) {
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            limparTela();
        }
    }

    private void limparTela() {
        txtCodAluno.setText("");
        txtDescAluno.setText("");
        txtCodCurso.setText("");
        txtDescCurso.setText("");

        aluno = new Aluno();
        curso = new Curso();
        cursoAluno = new CursoAluno();
    }

    private void buscarAlunoPorCodigo() {
        if (!txtCodAluno.getText().isEmpty()) {
            aluno = new Aluno();
            aluno.setCodigo(Integer.parseInt(txtCodAluno.getText()));
            aluno = alunoController.buscarPorCodigo(aluno);
            if (aluno.getCodigo() <= 0) {
                JOptionPane.showMessageDialog(null, "Nao Encontrado!");
                aluno = null;
                txtCodAluno.setText("");
                txtDescAluno.setText("");
                txtCodAluno.requestFocus();
            } else {
                txtDescAluno.setText(aluno.getNome());
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
                txtDescCurso.setText("");
                txtCodCurso.requestFocus();
            } else {
                txtDescCurso.setText(curso.getDescricao());
            }
        } else {
            curso = null;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtDescAluno = new javax.swing.JTextField();
        txtCodAluno = new javax.swing.JTextField();
        btnBuscarAluno = new javax.swing.JButton();
        txtCodCurso = new javax.swing.JTextField();
        txtDescCurso = new javax.swing.JTextField();
        btnBuscarCurso = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnSair = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();

        setClosable(true);
        setTitle("Cadastro de Classe");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtDescAluno.setEditable(false);
        txtDescAluno.setBackground(new java.awt.Color(255, 255, 255));

        txtCodAluno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodAlunoFocusLost(evt);
            }
        });

        btnBuscarAluno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/buscar.png"))); // NOI18N
        btnBuscarAluno.setText("Buscar");
        btnBuscarAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarAlunoActionPerformed(evt);
            }
        });

        txtCodCurso.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodCursoFocusLost(evt);
            }
        });

        txtDescCurso.setEditable(false);
        txtDescCurso.setBackground(new java.awt.Color(255, 255, 255));

        btnBuscarCurso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/buscar.png"))); // NOI18N
        btnBuscarCurso.setText("Buscar");
        btnBuscarCurso.setToolTipText("");
        btnBuscarCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCursoActionPerformed(evt);
            }
        });

        jLabel1.setText("Aluno:");

        jLabel2.setText("Curso:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtCodAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDescAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscarAluno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtCodCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtDescCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBuscarCurso)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDescAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDescCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sair.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/salvar.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSalvar)
                .addGap(17, 17, 17)
                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnSair)
                .addComponent(btnSalvar))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarAlunoActionPerformed
        ConsultaAluno aluno = new ConsultaAluno(this);
        this.getParent().add(aluno);
        aluno.setVisible(true);
    }//GEN-LAST:event_btnBuscarAlunoActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnBuscarCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCursoActionPerformed
        ConsultaCurso curso = new ConsultaCurso(this);
        this.getParent().add(curso);
        curso.setVisible(true);
    }//GEN-LAST:event_btnBuscarCursoActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        salvar();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void txtCodAlunoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodAlunoFocusLost
        buscarAlunoPorCodigo();
    }//GEN-LAST:event_txtCodAlunoFocusLost

    private void txtCodCursoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodCursoFocusLost
        buscarCursoPorCodigo();
    }//GEN-LAST:event_txtCodCursoFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarAluno;
    private javax.swing.JButton btnBuscarCurso;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtCodAluno;
    private javax.swing.JTextField txtCodCurso;
    private javax.swing.JTextField txtDescAluno;
    private javax.swing.JTextField txtDescCurso;
    // End of variables declaration//GEN-END:variables
}
