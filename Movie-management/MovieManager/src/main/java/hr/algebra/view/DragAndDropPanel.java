/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package hr.algebra.view;

import hr.algebra.dal.ActorRepository;
import hr.algebra.dal.DirectorRepository;
import hr.algebra.dal.MovieActorRepository;
import hr.algebra.dal.MovieDirectorRepository;
import hr.algebra.dal.MovieRepository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.helper.GenericTransferable;
import hr.algebra.model.Actor;
import hr.algebra.model.Director;
import hr.algebra.model.Movie;
import hr.algebra.model.MovieActor;
import hr.algebra.model.MovieDirector;
import hr.algebra.utilities.MessageUtils;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.TransferHandler;

/**
 *
 * @author Lovric
 */
public class DragAndDropPanel extends javax.swing.JPanel {

    /**
     * Creates new form DragAndDropPanel
     */
    public DragAndDropPanel() {
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

        jScrollPane2 = new javax.swing.JScrollPane();
        lsAvailableActors = new javax.swing.JList<>();
        cbMovies = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        lsAvailableDirectors = new javax.swing.JList<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        lsAssignedDirectors = new javax.swing.JList<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        lsAssignedActors = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        lsAvailableActors.setDragEnabled(true);
        lsAvailableActors.setDropMode(javax.swing.DropMode.ON);
        jScrollPane2.setViewportView(lsAvailableActors);

        cbMovies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMoviesActionPerformed(evt);
            }
        });

        lsAvailableDirectors.setDragEnabled(true);
        lsAvailableDirectors.setDropMode(javax.swing.DropMode.ON);
        jScrollPane4.setViewportView(lsAvailableDirectors);

        lsAssignedDirectors.setDragEnabled(true);
        lsAssignedDirectors.setDropMode(javax.swing.DropMode.ON);
        jScrollPane5.setViewportView(lsAssignedDirectors);

        lsAssignedActors.setDragEnabled(true);
        lsAssignedActors.setDropMode(javax.swing.DropMode.ON);
        jScrollPane6.setViewportView(lsAssignedActors);

        jLabel1.setText("Available actors:");

        jLabel2.setText("Assigned actors:");

        jLabel3.setText("Select movie:");

        jLabel4.setText("Available directors:");

        jLabel5.setText("Assigned directors:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(210, 210, 210)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89)
                        .addComponent(cbMovies, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(326, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbMovies, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
        );
    }// </editor-fold>//GEN-END:initComponents

    private boolean isReadOnly;

    public DragAndDropPanel(boolean isReadOnly) {
        this.isReadOnly = isReadOnly;
        initComponents();
    }
    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        init();
    }//GEN-LAST:event_formComponentShown

    private void init() {
        initRepository();
        loadMovies();
        initDragAndDrop();

        cbMoviesActionPerformed(null);
    }

    private void cbMoviesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMoviesActionPerformed

        Movie selectedMovie = (Movie) cbMovies.getSelectedItem();

        if (selectedMovie != null) {
            try {
                int movieId = selectedMovie.getId();
                assignedActors = movieActorRepository.selectActorsForMovie(movieId);
                availableActors = movieActorRepository.selectActorsNotInMovie(movieId);
                assignedDirectors = movieDirectorRepository.selectDirectorsForMovie(movieId);
                availableDirectors = movieDirectorRepository.selectDirectorsNotInMovie(movieId);

                updateActorLists();
                updateDirectorLists();
            } catch (Exception ex) {
                ex.printStackTrace();
                MessageUtils.showErrorMessage("Loading Error", "Failed to load actors or directors for selected movie.");
            }
    }//GEN-LAST:event_cbMoviesActionPerformed

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Movie> cbMovies;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JList<Actor> lsAssignedActors;
    private javax.swing.JList<Director> lsAssignedDirectors;
    private javax.swing.JList<Actor> lsAvailableActors;
    private javax.swing.JList<Director> lsAvailableDirectors;
    // End of variables declaration//GEN-END:variables

    private List<Actor> availableActors = new ArrayList<>();
    private List<Actor> assignedActors = new ArrayList<>();
    private List<Director> availableDirectors = new ArrayList<>();
    private List<Director> assignedDirectors = new ArrayList<>();

    private MovieRepository movieRepository;
    private MovieActorRepository movieActorRepository;
    private MovieDirectorRepository movieDirectorRepository;
    private ActorRepository actorRepository;
    private DirectorRepository directorRepository;

    private void loadMovies() {
        try {
            List<Movie> movies = movieRepository.selectMovies();
            DefaultComboBoxModel<Movie> model = new DefaultComboBoxModel<>();
            movies.forEach(model::addElement);
            cbMovies.setModel(model);
            if (!movies.isEmpty()) {
                cbMovies.setSelectedIndex(0);
                cbMoviesActionPerformed(null);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            MessageUtils.showErrorMessage("Loading Error", "Failed to load movies from database.");
        }
    }

    private void initRepository() {
        movieRepository = RepositoryFactory.getRepository(MovieRepository.class);
        movieActorRepository = RepositoryFactory.getRepository(MovieActorRepository.class);
        movieDirectorRepository = RepositoryFactory.getRepository(MovieDirectorRepository.class);
        actorRepository = RepositoryFactory.getRepository(ActorRepository.class);
        directorRepository = RepositoryFactory.getRepository(DirectorRepository.class);
    }

    private void initDragAndDrop() {
        if (isReadOnly) {
            return;
        }
        initActorDragAndDrop();
        initDirectorDragAndDrop();
    }

    private void updateActorLists() {
        Set<Actor> uniqueAssigned = new LinkedHashSet<>(assignedActors);
        Set<Actor> uniqueAvailable = new LinkedHashSet<>(availableActors);

        DefaultListModel<Actor> availableModel = new DefaultListModel<>();
        uniqueAvailable.forEach(availableModel::addElement);
        lsAvailableActors.setModel(availableModel);

        DefaultListModel<Actor> assignedModel = new DefaultListModel<>();
        uniqueAssigned.forEach(assignedModel::addElement);
        lsAssignedActors.setModel(assignedModel);

    }

    private void updateDirectorLists() {
        Set<Director> uniqueAssigned = new LinkedHashSet<>(assignedDirectors);
        Set<Director> uniqueAvailable = new LinkedHashSet<>(availableDirectors);

        DefaultListModel<Director> availableModel = new DefaultListModel<>();
        uniqueAvailable.forEach(availableModel::addElement);
        lsAvailableDirectors.setModel(availableModel);

        DefaultListModel<Director> assignedModel = new DefaultListModel<>();
        uniqueAssigned.forEach(assignedModel::addElement);
        lsAssignedDirectors.setModel(assignedModel);
    }

    private void initActorDragAndDrop() {
        lsAvailableActors.setDropMode(DropMode.ON);
        lsAvailableActors.setDragEnabled(true);

        lsAssignedActors.setDropMode(DropMode.ON);
        lsAssignedActors.setDragEnabled(true);

        TransferHandler handlerToAssigned = GenericTransferable.createTransferHandler((Actor a) -> {
            Movie selectedMovie = (Movie) cbMovies.getSelectedItem();
            if (selectedMovie == null || a == null) {
                return;
            }

            try {
                movieActorRepository.createMovieActor(new MovieActor(selectedMovie.getId(), a.getId()));
                assignedActors = movieActorRepository.selectActorsForMovie(selectedMovie.getId());
                availableActors = movieActorRepository.selectActorsNotInMovie(selectedMovie.getId());
                updateActorLists();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        TransferHandler handlerToAvailable = GenericTransferable.createTransferHandler((Actor a) -> {
            Movie selectedMovie = (Movie) cbMovies.getSelectedItem();
            if (selectedMovie == null || a == null) {
                return;
            }

            try {
                movieActorRepository.deleteMovieActor(selectedMovie.getId(), a.getId());
                assignedActors = movieActorRepository.selectActorsForMovie(selectedMovie.getId());
                availableActors = movieActorRepository.selectActorsNotInMovie(selectedMovie.getId());
                updateActorLists();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        lsAvailableActors.setTransferHandler(handlerToAssigned);
        lsAssignedActors.setTransferHandler(handlerToAvailable);

        lsAssignedActors.setTransferHandler(handlerToAssigned);
        lsAvailableActors.setTransferHandler(handlerToAvailable);

    }

    private void initDirectorDragAndDrop() {
        lsAvailableDirectors.setDropMode(DropMode.ON);
        lsAvailableDirectors.setDragEnabled(true);

        lsAssignedDirectors.setDropMode(DropMode.ON);
        lsAssignedDirectors.setDragEnabled(true);

        TransferHandler handlerToAssigned = GenericTransferable.createTransferHandler((Director d) -> {
            Movie selectedMovie = (Movie) cbMovies.getSelectedItem();
            if (selectedMovie == null || d == null) {
                return;
            }

            try {
                movieDirectorRepository.createMovieDirector(new MovieDirector(selectedMovie.getId(), d.getId()));
                assignedDirectors = movieDirectorRepository.selectDirectorsForMovie(selectedMovie.getId());
                availableDirectors = movieDirectorRepository.selectDirectorsNotInMovie(selectedMovie.getId());
                updateDirectorLists();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        TransferHandler handlerToAvailable = GenericTransferable.createTransferHandler((Director d) -> {
            Movie selectedMovie = (Movie) cbMovies.getSelectedItem();
            if (selectedMovie == null || d == null) {
                return;
            }

            try {
                movieDirectorRepository.deleteMovieDirector(selectedMovie.getId(), d.getId());
                assignedDirectors = movieDirectorRepository.selectDirectorsForMovie(selectedMovie.getId());
                availableDirectors = movieDirectorRepository.selectDirectorsNotInMovie(selectedMovie.getId());
                updateDirectorLists();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        lsAvailableDirectors.setTransferHandler(handlerToAssigned);
        lsAssignedDirectors.setTransferHandler(handlerToAvailable);

        lsAssignedDirectors.setTransferHandler(handlerToAssigned);
        lsAvailableDirectors.setTransferHandler(handlerToAvailable);
    }
}
