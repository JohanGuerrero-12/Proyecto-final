/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package systemnotas.SystemNotasVisual;

import javax.swing.*;
import java.awt.*;
import systemnotas.Profesor;
import systemnotas.SistemaNotas;

/**
 *
 * @author 57322
 */
public class VentanaProfesor extends javax.swing.JFrame {
    private SistemaNotas sistema;
    private Profesor profesor;

    public VentanaProfesor(Profesor profesor, SistemaNotas sistema) {
        this.profesor = profesor;
        this.sistema = sistema;
        setTitle("Panel del Profesor - " + profesor.getNombre());
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Panel principal con BorderLayout
        setLayout(new BorderLayout(10, 10));
        
        // Panel superior con título y botón de cerrar sesión
        JPanel panelSuperior = new JPanel(new BorderLayout());
        JLabel lblTitulo = new JLabel("Panel del Profesor - " + profesor.getNombre(), JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        panelSuperior.add(lblTitulo, BorderLayout.CENTER);
        panelSuperior.add(btnCerrarSesion, BorderLayout.EAST);
        
        // Panel central con pestañas
        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Pestaña 1: Mis Materias
        JPanel panelMaterias = new JPanel(new BorderLayout());
        JButton btnVerMaterias = new JButton("Ver Mis Materias");
        JTextArea areaMaterias = new JTextArea();
        areaMaterias.setEditable(false);
        areaMaterias.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollMaterias = new JScrollPane(areaMaterias);
        scrollMaterias.setBorder(BorderFactory.createTitledBorder("Materias Asignadas"));
        
        panelMaterias.add(btnVerMaterias, BorderLayout.NORTH);
        panelMaterias.add(scrollMaterias, BorderLayout.CENTER);
        
        // Pestaña 2: Calificar Estudiantes
        JPanel panelCalificaciones = new JPanel(new GridLayout(8, 2, 10, 10));
        panelCalificaciones.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JTextField estUsername = new JTextField();
        JComboBox<String> comboMaterias = new JComboBox<>();
        JTextField nota1 = new JTextField();
        JTextField nota2 = new JTextField();
        JTextField nota3 = new JTextField();
        JTextField notaParcial = new JTextField();
        JButton btnCalificar = new JButton("Registrar Calificaciones");
        JButton btnVerEstudiantes = new JButton("Ver Estudiantes de la Materia");
        
        panelCalificaciones.add(new JLabel("Username Estudiante:"));
        panelCalificaciones.add(estUsername);
        panelCalificaciones.add(new JLabel("Materia:"));
        panelCalificaciones.add(comboMaterias);
        panelCalificaciones.add(new JLabel("Nota 1:"));
        panelCalificaciones.add(nota1);
        panelCalificaciones.add(new JLabel("Nota 2:"));
        panelCalificaciones.add(nota2);
        panelCalificaciones.add(new JLabel("Nota 3:"));
        panelCalificaciones.add(nota3);
        panelCalificaciones.add(new JLabel("Parcial:"));
        panelCalificaciones.add(notaParcial);
        panelCalificaciones.add(btnCalificar);
        panelCalificaciones.add(btnVerEstudiantes);
        
        tabbedPane.addTab("Mis Materias", panelMaterias);
        tabbedPane.addTab("Calificar Estudiantes", panelCalificaciones);
        
        add(panelSuperior, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
        
        // Eventos
        btnCerrarSesion.addActionListener(e -> {
            int opcion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres cerrar sesión?", 
                "Cerrar Sesión", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                this.dispose();
                new VentanaLogin(sistema).setVisible(true);
            }
        });
        
        btnVerMaterias.addActionListener(e -> {
            StringBuilder materias = new StringBuilder("Materias asignadas:\n\n");
            if (profesor.getListaMaterias().isEmpty()) {
                materias.append("No tienes materias asignadas.");
            } else {
                for (int i = 0; i < profesor.getListaMaterias().size(); i++) {
                    materias.append((i + 1)).append(". ").append(profesor.getListaMaterias().get(i).getNombre()).append("\n");
                }
            }
            areaMaterias.setText(materias.toString());
        });
        
        // Actualizar combo de materias cuando se abre la pestaña
        tabbedPane.addChangeListener(e -> {
            if (tabbedPane.getSelectedIndex() == 1) { // Pestaña de calificaciones
                comboMaterias.removeAllItems();
                for (systemnotas.Materia materia : profesor.getListaMaterias()) {
                    comboMaterias.addItem(materia.getNombre());
                }
            }
        });
        
        btnCalificar.addActionListener(e -> {
            try {
                String usernameEst = estUsername.getText().trim();
                String nombreMat = (String) comboMaterias.getSelectedItem();
                double n1 = Double.parseDouble(nota1.getText());
                double n2 = Double.parseDouble(nota2.getText());
                double n3 = Double.parseDouble(nota3.getText());
                double parcial = Double.parseDouble(notaParcial.getText());
                
                if (n1 < 0 || n1 > 5 || n2 < 0 || n2 > 5 || n3 < 0 || n3 > 5 || parcial < 0 || parcial > 5) {
                    JOptionPane.showMessageDialog(this, "Las notas deben estar entre 0 y 5", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                String res = sistema.calificarEstudiante(usernameEst, nombreMat, n1, n2, n3, parcial);
                JOptionPane.showMessageDialog(this, res);
                
                if (res.contains("exitosamente")) {
                    estUsername.setText("");
                    nota1.setText("");
                    nota2.setText("");
                    nota3.setText("");
                    notaParcial.setText("");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese números válidos para las notas", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        btnVerEstudiantes.addActionListener(e -> {
            String nombreMat = (String) comboMaterias.getSelectedItem();
            if (nombreMat == null || nombreMat.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Seleccione una materia primero", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            StringBuilder estudiantes = new StringBuilder("Estudiantes inscritos en " + nombreMat + ":\n\n");
            java.util.ArrayList<systemnotas.Inscripcion> inscripciones = sistema.getInscripcionesPorMateria(nombreMat);
            
            if (inscripciones.isEmpty()) {
                estudiantes.append("No hay estudiantes inscritos en esta materia.");
            } else {
                for (int i = 0; i < inscripciones.size(); i++) {
                    systemnotas.Inscripcion insc = inscripciones.get(i);
                    estudiantes.append((i + 1)).append(". ").append(insc.getEstudiante().getNombre())
                        .append(" (").append(insc.getEstudiante().getNickname()).append(")\n");
                    estudiantes.append("   Notas: N1=").append(insc.getN1())
                        .append(", N2=").append(insc.getN2())
                        .append(", N3=").append(insc.getN3())
                        .append(", Parcial=").append(insc.getParcial())
                        .append(" | Final: ").append(String.format("%.2f", insc.calcularFinal())).append("\n\n");
                }
            }
            
            JOptionPane.showMessageDialog(this, estudiantes.toString(), "Estudiantes de " + nombreMat, JOptionPane.INFORMATION_MESSAGE);
        });
        
        // Cargar materias automáticamente
        btnVerMaterias.doClick();
    }

    /**
     * Creates new form VentanaProfesor
     */
    public VentanaProfesor() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(VentanaProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaProfesor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
