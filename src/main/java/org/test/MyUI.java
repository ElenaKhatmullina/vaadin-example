package org.test;

import javax.servlet.annotation.WebServlet;

import com.vaadin.addon.pagination.Pagination;
import com.vaadin.addon.pagination.PaginationChangeListener;
import com.vaadin.addon.pagination.PaginationResource;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.test.backend.entity.Scientist;
import org.test.backend.service.ScientistService;
import kotlin.*;
import org.test.backend.util.FirstMyKotlin;
import org.test.backend.util.ForLearnLotlin;


import java.util.List;

@Theme("mytheme")
public class MyUI extends UI {

    private ScientistService scientistService = ScientistService.getInstance();

    private final TextField scientistName = new TextField("имя Великого ученого");
    private final TextField  discovery = new TextField("Его открытие");

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        DataProvider<Scientist, Void> dataProvider =
                DataProvider.fromCallbacks(
                        // First callback fetches items based on a query
                        query -> {
                            // The index of the first item to load
                            int offset = query.getOffset();

                            // The number of items to load
                            int limit = query.getLimit();

                            List<Scientist> scientists0 = getScientistService()
                                    .findPage(offset, limit);

                            return scientists0.stream();
                        },
                        // Second callback fetches the total number of items currently in the Grid.
                        // The grid can then use it to properly adjust the scrollbars.
                        query -> getScientistService().getIntCount());

        system.out.println("WORK5555!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")

        Grid<Scientist> grid = new Grid<>();
        grid.setSizeFull();
        /*grid.setColumns("newId","name","discovery");*/
        grid.addColumn(Scientist::getNewId);
        grid.addColumn(Scientist::getName);
        grid.addColumn(Scientist::getDiscovery);
// add some Beans to grid
        grid.recalculateColumnWidths();
        grid.setDataProvider(dataProvider);

        final Scientist scientist = new Scientist();

        Button save;
        final VerticalLayout layout = new VerticalLayout();

        Label label=new Label("Добавление еще одного великого ученого в список");
        Label label3=new Label("Получила из котлин класса: "+ new FirstMyKotlin().getNumberFive());
        label.addStyleName ( ValoTheme.LABEL_H3 );
        layout.addComponent(label);
        layout.addComponent(label3);

        scientistName.setWidth("100%");

        discovery.setWidth("100%");

        save = new Button("Save");
        save.setWidth("100%");

        save.addClickListener(event -> {
            if ((scientistName.getValue()!=null)&&(discovery.getValue()!=null)&&(!scientistName.getValue().equals(""))&&(!discovery.getValue().equals(""))) {
                scientist.setName(scientistName.getValue());
                scientist.setDiscovery(discovery.getValue());
                scientistService.add(scientist);

                Notification.show(scientistName.getValue()+ (" добавлен в список"));

                scientistName.setValue("");
                scientistName.clear();
                discovery.setValue("");
                discovery.clear();

                final long total2 = scientistService.getCount();
            }
            else{Notification.show("не все поля заполнены!", Notification.Type.ERROR_MESSAGE);}
        });

        Label label2=new Label("Список уже добавленных ученых");
        label2.addStyleName ( ValoTheme.LABEL_H3 );
        setSizeFull();
        layout.addComponents(scientistName,discovery,save,label2);
        layout.addComponent(grid);
      new  ForLearnLotlin().sayHello();
        setContent(layout);
    }

    private ScientistService getScientistService() {
        return scientistService;
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
