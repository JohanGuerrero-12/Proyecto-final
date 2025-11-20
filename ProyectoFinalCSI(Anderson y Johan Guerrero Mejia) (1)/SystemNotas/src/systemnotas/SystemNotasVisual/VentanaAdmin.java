package systemnotas.SystemNotasVisual;

import javax.swing.*;
import java.awt.*;
import systemnotas.SistemaNotas;



/**
 *
 * @author 57322
 */
public class VentanaAdmin extends javax.swing.JFrame {
    private SistemaNotas sistema;

    public VentanaAdmin(SistemaNotas sistema) {
        this.sistema = sistema;
        setTitle("Panel de Administrador");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Panel principal con BorderLayout
        setLayout(new BorderLayout(10, 10));
        
        // Panel superior con título y botón de cerrar sesión
        JPanel panelSuperior = new JPanel(new BorderLayout());
        JLabel lblTitulo = new JLabel("Panel de Administrador", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        panelSuperior.add(lblTitulo, BorderLayout.CENTER);
        panelSuperior.add(btnCerrarSesion, BorderLayout.EAST);
        
        // Panel central con pestañas
        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Pestaña 1: Registrar Usuarios
        JPanel panelRegistro = new JPanel(new GridLayout(5, 2, 10, 10));
        panelRegistro.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JTextField nombre = new JTextField();
        JTextField user = new JTextField();
        JTextField pass = new JTextField();
        JButton btnRegEst = new JButton("Registrar Estudiante");
        JButton btnRegProf = new JButton("Registrar Profesor");

        panelRegistro.add(new JLabel("Nombre Completo:"));
        panelRegistro.add(nombre);
        panelRegistro.add(new JLabel("Username:"));
        panelRegistro.add(user);
        panelRegistro.add(new JLabel("Password:"));
        panelRegistro.add(pass);
        panelRegistro.add(btnRegEst);
        panelRegistro.add(btnRegProf);
        
        // Pestaña 2: Gestionar Materias
        JPanel panelMaterias = new JPanel(new GridLayout(3, 2, 10, 10));
        panelMaterias.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JTextField nombreMateria = new JTextField();
        JButton btnCrearMateria = new JButton("Crear Materia");
        JButton btnVerMaterias = new JButton("Ver Todas las Materias");
        
        panelMaterias.add(new JLabel("Nombre de Materia:"));
        panelMaterias.add(nombreMateria);
        panelMaterias.add(btnCrearMateria);
        panelMaterias.add(btnVerMaterias);
        panelMaterias.add(new JLabel());
        panelMaterias.add(new JLabel());
        
        // Pestaña 3: Inscripciones
        JPanel panelInscripciones = new JPanel(new GridLayout(4, 2, 10, 10));
        panelInscripciones.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JTextField estUsername = new JTextField();
        JTextField matNombre = new JTextField();
        JButton btnInscribir = new JButton("Inscribir Estudiante");
        JButton btnVerInscripciones = new JButton("Ver Inscripciones");
        
        panelInscripciones.add(new JLabel("Username Estudiante:"));
        panelInscripciones.add(estUsername);
        panelInscripciones.add(new JLabel("Nombre Materia:"));
        panelInscripciones.add(matNombre);
        panelInscripciones.add(btnInscribir);
        panelInscripciones.add(btnVerInscripciones);
        
        // Pestaña 4: Asignar Materias a Profesores
        JPanel panelAsignarProf = new JPanel(new GridLayout(4, 2, 10, 10));
        panelAsignarProf.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JTextField profUsername = new JTextField();
        JTextField matNombreProf = new JTextField();
        JButton btnAsignarProf = new JButton("Asignar Materia a Profesor");
        JButton btnVerAsignaciones = new JButton("Ver Asignaciones de Profesores");
        
        panelAsignarProf.add(new JLabel("Username Profesor:"));
        panelAsignarProf.add(profUsername);
        panelAsignarProf.add(new JLabel("Nombre Materia:"));
        panelAsignarProf.add(matNombreProf);
        panelAsignarProf.add(btnAsignarProf);
        panelAsignarProf.add(btnVerAsignaciones);
        
        tabbedPane.addTab("Registrar Usuarios", panelRegistro);
        tabbedPane.addTab("Gestionar Materias", panelMaterias);
        tabbedPane.addTab("Inscripciones", panelInscripciones);
        tabbedPane.addTab("Asignar a Profesores", panelAsignarProf);
        
        add(panelSuperior, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);

        // Eventos para botones de registro
        btnRegEst.addActionListener(e -> {
            String res = sistema.registrarEstudiante(nombre.getText(), user.getText(), pass.getText());
            JOptionPane.showMessageDialog(this, res);
            if (res.contains("éxito")) {
                nombre.setText("");
                user.setText("");
                pass.setText("");
            }
        });
        
        btnRegProf.addActionListener(e -> {
            String res = sistema.registrarProfesor(nombre.getText(), user.getText(), pass.getText());
            JOptionPane.showMessageDialog(this, res);
            if (res.contains("éxito")) {
                nombre.setText("");
                user.setText("");
                pass.setText("");
            }
        });
        
        // Eventos para gestión de materias
        btnCrearMateria.addActionListener(e -> {
            String res = sistema.crearMateria(nombreMateria.getText());
            JOptionPane.showMessageDialog(this, res);
            if (res.contains("éxito")) {
                nombreMateria.setText("");
            }
        });
        
        btnVerMaterias.addActionListener(e -> {
            StringBuilder materias = new StringBuilder("Materias registradas:\n\n");
            if (sistema.materias.isEmpty()) {
                materias.append("No hay materias registradas.");
            } else {
                for (int i = 0; i < sistema.materias.size(); i++) {
                    materias.append((i + 1)).append(". ").append(sistema.materias.get(i).getNombre()).append("\n");
                }
            }
            JOptionPane.showMessageDialog(this, materias.toString(), "Lista de Materias", JOptionPane.INFORMATION_MESSAGE);
        });
        
        // Eventos para inscripciones
        btnInscribir.addActionListener(e -> {
            String res = sistema.inscribirEstudianteEnMateria(estUsername.getText(), matNombre.getText());
            JOptionPane.showMessageDialog(this, res);
            if (res.contains("exitoso")) {
                estUsername.setText("");
                matNombre.setText("");
            }
        });
        
        btnVerInscripciones.addActionListener(e -> {
            StringBuilder inscripciones = new StringBuilder("Inscripciones actuales:\n\n");
            if (sistema.inscripciones.isEmpty()) {
                inscripciones.append("No hay inscripciones registradas.");
            } else {
                for (int i = 0; i < sistema.inscripciones.size(); i++) {
                    systemnotas.Inscripcion insc = sistema.inscripciones.get(i);
                    inscripciones.append((i + 1)).append(". ")
                        .append(insc.getEstudiante().getNickname())
                        .append(" -> ")
                        .append(insc.getMateria().getNombre())
                        .append("\n");
                }
            }
            JOptionPane.showMessageDialog(this, inscripciones.toString(), "Lista de Inscripciones", JOptionPane.INFORMATION_MESSAGE);
        });
        
        // Eventos para asignación de materias a profesores
        btnAsignarProf.addActionListener(e -> {
            String res = sistema.asignarMateriaAProfesor(profUsername.getText(), matNombreProf.getText());
            JOptionPane.showMessageDialog(this, res);
            if (res.contains("exitosamente")) {
                profUsername.setText("");
                matNombreProf.setText("");
            }
        });
        
        btnVerAsignaciones.addActionListener(e -> {
            StringBuilder asignaciones = new StringBuilder("Asignaciones de Profesores:\n\n");
            boolean hayAsignaciones = false;
            
            for (systemnotas.Persona usuario : sistema.usuarios) {
                if (usuario instanceof systemnotas.Profesor) {
                    systemnotas.Profesor prof = (systemnotas.Profesor) usuario;
                    if (!prof.getListaMaterias().isEmpty()) {
                        hayAsignaciones = true;
                        asignaciones.append("Profesor: ").append(prof.getNombre()).append(" (").append(prof.getNickname()).append(")\n");
                        for (systemnotas.Materia materia : prof.getListaMaterias()) {
                            asignaciones.append("  - ").append(materia.getNombre()).append("\n");
                        }
                        asignaciones.append("\n");
                    }
                }
            }
            
            if (!hayAsignaciones) {
                asignaciones.append("No hay asignaciones de materias a profesores.");
            }
            
            JOptionPane.showMessageDialog(this, asignaciones.toString(), "Asignaciones de Profesores", JOptionPane.INFORMATION_MESSAGE);
        });
        
        // Evento para cerrar sesión
        btnCerrarSesion.addActionListener(e -> {
            int opcion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres cerrar sesión?", 
                "Cerrar Sesión", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                this.dispose();
                new systemnotas.SystemNotasVisual.VentanaLogin(sistema).setVisible(true);
            }
        });
    }



    /**
     * Creates new form VentanaAdmin
     */
    public VentanaAdmin() {
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
            java.util.logging.Logger.getLogger(VentanaAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
