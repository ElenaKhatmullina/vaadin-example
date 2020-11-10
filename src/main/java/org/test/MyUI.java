package org.test;

import javax.servlet.annotation.WebServlet;

import com.vaadin.addon.pagination.Pagination;
import com.vaadin.addon.pagination.PaginationChangeListener;
import com.vaadin.addon.pagination.PaginationResource;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.test.backend.entity.Scientist;
import org.test.backend.service.ScientistService;
import kotlin.*;
import org.test.backend.util.FirstMyKotlin;

import java.util.List;

@Theme("mytheme")
public class MyUI extends UI {

    private ScientistService scientistService = ScientistService.getInstance();

    private final TextField scientistName = new TextField("имя Великого ученого");
    private final TextField  discovery = new TextField("Его открытие");

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final int page = 1;
        final int limit = 10;

        final List<Scientist> scientists = scientistService.findPage(0, limit);
        final long total = scientistService.getCount();
        System.out.println("total: "+total);

        final Grid <Scientist> table = createTable(scientists);
        final Pagination pagination = createPagination(total, page, limit);
        pagination.addPageChangeListener((PaginationChangeListener) event -> table.setItems(scientistService.findPage(event.fromIndex(), limit)));

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
            if ((scientistName.getValue()!=null)&&(discovery.getValue()!=null)&&(scientistName.getValue()!="")&&(discovery.getValue()!="")) {
                scientist.setName(scientistName.getValue().toString());
                scientist.setDiscovery(discovery.getValue().toString());
                scientistService.add(scientist);

                Notification.show(scientistName.getValue()+ (" добавлен в список"));

                scientistName.setValue("");
                scientistName.clear();
                discovery.setValue("");
                discovery.clear();

                final long total2 = scientistService.getCount();
                int fromIndex=((int)(total2/limit))*limit;

                final List<Scientist> scientists2 = scientistService.findPage(fromIndex, limit);
                pagination.lastClick();
                table.setItems(scientists2);
            }
            else{Notification.show("не все поля заполнены!", Notification.Type.ERROR_MESSAGE);}
        });
        Label label2=new Label("Список уже добавленных ученых");
        label2.addStyleName ( ValoTheme.LABEL_H3 );
        setSizeFull();
        layout.addComponents(scientistName,discovery,save,label2,pagination,table);
        setContent(layout);
    }

    private  Grid <Scientist> createTable(List<Scientist> users) {

        final Grid<Scientist> table = new Grid<>(Scientist.class);
        table.setSizeFull();
        table.setSizeFull();
        table.setColumns("newId","name","discovery");
        table.recalculateColumnWidths();
        table.setItems(users);

        return table;
    }

    private Pagination createPagination(long total, int page, int limit) {
        final PaginationResource paginationResource = PaginationResource.newBuilder().setTotal(total).setPage(page).setLimit(limit).build();
        final Pagination pagination = new Pagination(paginationResource);
        pagination.setItemsPerPage(10, 20, 50, 100);
        return pagination;
    }


    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
