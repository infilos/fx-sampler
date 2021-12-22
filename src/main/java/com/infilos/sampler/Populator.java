package com.infilos.sampler;

import com.infilos.sampler.layout.*;
import com.infilos.sampler.provider.*;
import com.infilos.sampler.util.Consts;
import com.infilos.sampler.util.FileUtil;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Populator {
    private static final List<ColumnControlProvider<?>> COLUMN_CONTROL_PROVIDERS = new ArrayList<>();
    private static final List<MultiformControlProvider> VARIABLE_CONTROL_PROVIDERS = new ArrayList<>();
    private static final Column[] COLUMNS = Column.values();
    private static final int MAX_WIDTH = 120;

    static {
        COLUMN_CONTROL_PROVIDERS.add(new LabelProvider());
        COLUMN_CONTROL_PROVIDERS.add(new ButtonProvider());
        COLUMN_CONTROL_PROVIDERS.add(new CheckboxProvider());
        COLUMN_CONTROL_PROVIDERS.add(new RadioProvider());
        COLUMN_CONTROL_PROVIDERS.add(new ComboboxProvider());
        COLUMN_CONTROL_PROVIDERS.add(new SpinnerProvider());
        COLUMN_CONTROL_PROVIDERS.add(new TextfieldProvider());
        VARIABLE_CONTROL_PROVIDERS.add(new TabsProvider());
        VARIABLE_CONTROL_PROVIDERS.add(new SliderProvider());
        VARIABLE_CONTROL_PROVIDERS.add(new ProgressProvider(false));
        VARIABLE_CONTROL_PROVIDERS.add(new ProgressProvider(true));
        VARIABLE_CONTROL_PROVIDERS.add(new TableProvider());
        VARIABLE_CONTROL_PROVIDERS.add(new ListProvider());
        VARIABLE_CONTROL_PROVIDERS.add(new TreeProvider());
    }

    public static void apply(Stage stage) {
        BorderPane root = new BorderPane();
        populateMenu(stage, root);
        populateControls(root);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setWidth(800);
        stage.setHeight(400);
    }

    /**
     * @param stage Stage to apply menu to.
     * @param root  Root to add the menu to.
     */
    private static void populateMenu(Stage stage, BorderPane root) {
        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("File");
        Menu miscMenu = new Menu("Misc");
        Menu helpMenu = new Menu("Help");

        MenuItem setStyle = new MenuItem("Set stylesheet");
        setStyle.setOnAction(e -> FileUtil.setStyle(stage, menuBar.getScene()));
        fileMenu.getItems().add(setStyle);

        miscMenu.getItems().add(new CheckMenuItem("Checked item"));
        miscMenu.getItems().add(new SeparatorMenuItem());
        miscMenu.getItems().add(new RadioMenuItem("Radio item"));

        MenuItem openGithub = new MenuItem("GitHub");
        openGithub.setOnAction(e -> FileUtil.browse(Consts.GITHUB_URL));
        
        helpMenu.getItems().add(openGithub);

        menuBar.getMenus().add(fileMenu);
        menuBar.getMenus().add(miscMenu);
        menuBar.getMenus().add(helpMenu);

        root.setTop(menuBar);
    }

    /**
     * @param root Root to add controls to.
     */
    private static void populateControls(BorderPane root) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(15);
        grid.setHgap(30);
        
        int row = 0;
        for (ColumnControlProvider<?> provider : COLUMN_CONTROL_PROVIDERS) {
            int col = 0;
            Node[] children = new Node[COLUMNS.length + 1];
            children[0] = provider.key();
            for (Column column : COLUMNS) {
                Node child = provider.create(column);
                if (child == null)
                    child = new Label();
                if (child instanceof Region)
                    ((Region) child).setMaxWidth(MAX_WIDTH);
                children[++col] = child;
            }
            grid.addRow(row++, children);
        }
        for (MultiformControlProvider provider : VARIABLE_CONTROL_PROVIDERS) {
            provider.accept(grid, row++);
        }
        
        root.setCenter(new ScrollPane(grid));
    }
}
