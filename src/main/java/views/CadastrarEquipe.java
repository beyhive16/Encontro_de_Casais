/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import dao.BaseDAO;
import dao.CasalDAO;
import dao.CasalDAOImp;
import dao.EncontroDAO;
import dao.EncontroDAOImp;
import dao.EquipeDAO;
import dao.EquipeDAOImp;
import dao.PessoaDAO;
import dao.PessoaDAOImp;
import dao.TipoEquipeDAO;
import dao.TipoEquipeDAOImp;
import entity.Encontro;
import entity.Equipe;
import entity.Pessoa;
import entity.TipoEquipe;
import util.Datas;

/**
 *
 * @author raquelmelo
 */
public class CadastrarEquipe extends javax.swing.JFrame {

    /**
     * Creates new form tela9
     */
	
	private BaseDAO baseDAO;
	
	private String[] tipoEquipes = null;
	private String tipoFiltro;
	private String[][] casais = null;
	
	private TipoEquipe tEquipe = null;
	private Encontro encontro = null;
	private Pessoa padre = null;
	
    public CadastrarEquipe() {
        try {
			baseDAO = new BaseDAO();
			TipoEquipeDAO tipoEquipeDAO = new TipoEquipeDAOImp(baseDAO);
			EncontroDAO encontroDAO = new EncontroDAOImp(baseDAO);
			PessoaDAO pessoaDAO = new PessoaDAOImp(baseDAO);
			TipoEquipe tipoEquipe = tipoEquipeDAO.buscarPorNome("Acolhida");
			
			EquipeDAO equipeDAO = new EquipeDAOImp(baseDAO);
			
			CasalDAO casalDAO = new CasalDAOImp(baseDAO);
			
			this.tipoEquipes = tipoEquipeDAO.buscarTodosTipoEquipe();
			this.encontro = encontroDAO.buscarUltimoEncontro();
			this.padre = pessoaDAO.buscarPorId(encontro.getOrientadorEspiritual().getId());
			
			Equipe equipe = equipeDAO.selecionarPorTipo(tipoEquipe, encontro, Datas.anoAtual());
			this.casais = casalDAO.selecionarPorNomeEquipe("Acolhida");
			
			encontro.setOrientadorEspiritual(padre);
			baseDAO.getConnection().close();
			initComponents();
			jTextField_padre1.setText(padre.getNomeUsual());
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel12 = new javax.swing.JLabel();
        jTextField_padre = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextField_casalADM = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField_padre1 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField_casalADM1 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();

        jLabel12.setText("Diretor Espiritual");

        jTextField_padre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField_padreMouseClicked(evt);
            }
        });
        jTextField_padre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_padreActionPerformed(evt);
            }
        });

        jLabel13.setText("Coordenador Geral");

        jTextField_casalADM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_casalADMActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(27, 187, 125));

        jLabel1.setFont(new java.awt.Font("Century Schoolbook L", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("REGISTRO DE ENCONTRO");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField_padre1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField_padre1MouseClicked(evt);
            }
        });
        jTextField_padre1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_padre1ActionPerformed(evt);
            }
        });

        jLabel14.setText("Diretor Espiritual");

        jTextField_casalADM1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_casalADM1ActionPerformed(evt);
            }
        });

        jLabel15.setText("Coordenador Geral");

        jPanel2.setBackground(new java.awt.Color(27, 187, 125));

        jLabel2.setFont(new java.awt.Font("Century Schoolbook L", 1, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("REGISTRO DE ENCONTRO");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(tipoEquipes));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Selecione a Equipe");

        jButton1.setText("ADICIONAR INTEGRANTES");
        jButton1.setToolTipText("");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("+");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("+");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            casais,
            new String [] {
                "APELIDO", "NOME DELA", "NOME DELE", "SELECIONAR"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton5.setText("REMOVER SELECIONADOS");
        jButton5.setToolTipText("");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextField_padre1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextField_casalADM1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel15)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5))
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(353, 353, 353)
                                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 5, Short.MAX_VALUE)))
                        .addGap(55, 55, 55))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_padre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_casalADM1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3))))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(15, 15, 15)
                .addComponent(jButton5)
                .addGap(0, 23, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_padreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField_padreMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_padreMouseClicked

    private void jTextField_padreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_padreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_padreActionPerformed

    private void jTextField_casalADMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_casalADMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_casalADMActionPerformed

    private void jTextField_padre1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField_padre1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_padre1MouseClicked

    private void jTextField_padre1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_padre1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_padre1ActionPerformed

    private void jTextField_casalADM1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_casalADM1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_casalADM1ActionPerformed

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseEntered

    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseEntered
    
   
	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        SelecionarCasais obj = new SelecionarCasais();
        obj.setVisible(true);
        obj.pack();
        obj.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        SelecionarPadres obj = new SelecionarPadres();
        obj.setVisible(true);
        obj.pack();
        obj.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        SelecionarCasais obj = new SelecionarCasais();
        obj.setVisible(true);
        obj.pack();
        obj.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int tamanho = model.getRowCount();
        Vector vetorDados = model.getDataVector();
        for (int i=0;i<tamanho;i++){
            Vector linha = (Vector) vetorDados.get(i);
            if (linha.contains(true)){
            	BaseDAO base;
				try {
					base = new BaseDAO();
					CasalDAO casalDAO = new CasalDAOImp(base);
					System.out.println("ANTES");
					System.out.println(linha.get(0).toString());
					casalDAO.removerCasalEquipe(linha.get(0).toString(), this.tipoFiltro);
					System.out.println("DEPOIS");
					base.getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }
//        this.setVisible(false);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int tamanho = model.getRowCount();
        Vector vetorDados = model.getDataVector();
        for (int i=0;i<tamanho;i++){
            Vector linha = (Vector) vetorDados.get(i);
            if (linha.contains(true)){
                System.out.println(linha);
                BaseDAO base;
				try {
					base = new BaseDAO();
					CasalDAO casalDAO = new CasalDAOImp(base);
					base.getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                //SE ENTRAR NESSE IF SIGNIFICA QUE TA SELECIONADO. AI TU ADD NO BANCO.
                //EM SEGUIDA A TELA FECHA, QUANDO ELE APERTAR PRA VER A EQUIPE DE ALGUEM, TU POPULA AQUELA TELINHA COM OS NOMES
            }
        }
//        this.setVisible(false);
    }//GEN-LAST:event_jButton1MouseClicked

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        this.tipoFiltro = jComboBox1.getSelectedItem().toString();
        System.out.println(tipoFiltro);
        
        TipoEquipe tipoEquipe = null;
        //&&& PARTE DO BANCO
        try {
			baseDAO = new BaseDAO();
			TipoEquipeDAO tipoEquipeDAO = new TipoEquipeDAOImp(baseDAO);
			tipoEquipe = tipoEquipeDAO.buscarPorNome(tipoFiltro);
			EquipeDAO equipeDAO = new EquipeDAOImp(baseDAO);
			CasalDAO casalDAO = new CasalDAOImp(baseDAO);
			Equipe equipe = equipeDAO.selecionarPorTipo(tipoEquipe, encontro, Datas.anoAtual());
			casais = casalDAO.selecionarPorNomeEquipe(this.tipoFiltro);
			
			System.out.println("ID EQUIPE:"+equipe.getId());
			try {
				baseDAO.getConnection().close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        //busca no banco através dessa String, ai em seguida ele limpa a tabela
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        
        //e aqui ele adiciona os dados que tu trouxe
        for(int i=0;i<casais.length;i++)
        {
        	Vector rowData = new Vector<String>(); //aqui tu faz um vetor com 4 posicoes, apelido-nome dele-nome dela, sendo a 4ª posicao null
        	rowData.add(casais[i][0]);
        	rowData.add(casais[i][1]);
        	rowData.add(casais[i][2]);
        	rowData.add(casais[i][3]);
        	model.addRow(rowData);        	
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int tamanho = model.getRowCount();
        Vector vetorDados = model.getDataVector();
        for (int i=0;i<tamanho;i++){
            Vector linha = (Vector) vetorDados.get(i);
            if (linha.contains(true)){
                System.out.println(linha);
                //SE ENTRAR NESSE IF SIGNIFICA QUE TA SELECIONADO. AI TU REMOVE NO BANCO.
            }
        }
    }//GEN-LAST:event_jButton5MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CadastrarEquipe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastrarEquipe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastrarEquipe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastrarEquipe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastrarEquipe().setVisible(true);
            }
        });
    }
    
    public String[][] todosCasais()
    {
    	String[][] todosCasal;
    	try {
			CasalDAO casalDAO = new CasalDAOImp(baseDAO);
			todosCasal = casalDAO.selecionarAll();
			return todosCasal;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    public javax.swing.JTextField jTextField_casalADM;
    public javax.swing.JTextField jTextField_casalADM1;
    public javax.swing.JTextField jTextField_padre;
    public javax.swing.JTextField jTextField_padre1;
    // End of variables declaration//GEN-END:variables
}
