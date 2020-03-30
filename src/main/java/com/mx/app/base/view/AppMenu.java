package com.mx.app.base.view;

import com.google.common.eventbus.Subscribe;
import com.mx.app.base.event.AppEvent.*;
import com.mx.app.base.event.AppEventBus;
import com.vaadin.server.*;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.themes.ValoTheme;

/**
 * A responsive menu component providing user information and the controls for
 * primary navigation between the views.
 */
@SuppressWarnings({"serial", "unchecked"})
public final class AppMenu extends CustomComponent {

    public static final String ID = "app-menu";
    private static final String STYLE_VISIBLE = "valo-menu-visible";
    private MenuItem settingsItem;

    public AppMenu() {
        setPrimaryStyleName("valo-menu");
        setId(ID);
        setSizeUndefined();

        // There's only one AppMenu per UI so this doesn't need to be
        // unregistered from the UI-scoped AppEventBus.
        AppEventBus.register(this);

        setCompositionRoot(buildContent());
    }

    private Component buildContent() {
        final CssLayout menuContent = new CssLayout();
        menuContent.addStyleName("sidebar");
        menuContent.addStyleName(ValoTheme.MENU_PART);
        menuContent.addStyleName("no-vertical-drag-hints");
        menuContent.addStyleName("no-horizontal-drag-hints");
        menuContent.setWidth(null);
        menuContent.setHeight("100%");

        menuContent.addComponent(buildTitle());
        menuContent.addComponent(buildLogo());
        menuContent.addComponent(buildToggleButton());
        menuContent.addComponent(buildMenuItems());

        return menuContent;
    }

    private Component buildTitle() {
        Label logo = new Label("<strong>Agenda</strong>", ContentMode.HTML);
        logo.setSizeUndefined();
        HorizontalLayout logoWrapper = new HorizontalLayout(logo);
        logoWrapper.setComponentAlignment(logo, Alignment.MIDDLE_CENTER);
        logoWrapper.addStyleName("valo-menu-title");
        logoWrapper.setSpacing(false);
        return logoWrapper;
    }

//    private User getCurrentUser() {
//        return (User) VaadinSession.getCurrent().getAttribute(User.class.getName());
//    }
    private Component buildLogo() {
        final MenuBar logo = new MenuBar();
        logo.addStyleName("user-menu");
        settingsItem = logo.addItem("", new ThemeResource("img/logo.png"), null);
        return logo;
    }

    private Component buildLogOutButton() {
        Button btnLogout = new Button("Logout");
        btnLogout.setPrimaryStyleName("valo-menu-item");
        btnLogout.setIcon(FontAwesome.POWER_OFF);
        btnLogout.addClickListener((ClickEvent event) -> {
//            VaadinSession.getCurrent().getSession().invalidate();
//            Page.getCurrent().reload();           // OTRA OPCION DE HACER LOGOUT
            AppEventBus.post(new UserLoggedOutEvent());
        });
        return btnLogout;
    }

    private Component buildToggleButton() {
        Button valoMenuToggleButton = new Button("Menu", new ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {
                if (getCompositionRoot().getStyleName()
                        .contains(STYLE_VISIBLE)) {
                    getCompositionRoot().removeStyleName(STYLE_VISIBLE);
                } else {
                    getCompositionRoot().addStyleName(STYLE_VISIBLE);
                }
            }
        });
        valoMenuToggleButton.setIcon(FontAwesome.LIST);
        valoMenuToggleButton.addStyleName("valo-menu-toggle");
        valoMenuToggleButton.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        valoMenuToggleButton.addStyleName(ValoTheme.BUTTON_SMALL);
        return valoMenuToggleButton;
    }

    private Component buildMenuItems() {
        CssLayout menuItemsLayout = new CssLayout();
        menuItemsLayout.addStyleName("valo-menuitems");

        for (final AppViewType view : AppViewType.values()) {
            Component menuItemComponent = new ValoMenuItemButton(view);
            if (view.isVisibleInMenu()) {
                menuItemsLayout.addComponent(menuItemComponent);
            }
        }
        menuItemsLayout.addComponent(buildLogOutButton());
        return menuItemsLayout;

    }

    @Override
    public void attach() {
        super.attach();
    }

    @Subscribe
    public void postViewChange(final PostViewChangeEvent event) {
        // After a successful view change the menu can be hidden in mobile view.
        getCompositionRoot().removeStyleName(STYLE_VISIBLE);
    }

//    @Subscribe
//    public void updateUserName(final ProfileUpdatedEvent event) {
//        User user = getCurrentUser();
//        settingsItem.setText(user.getFirstName() + " " + user.getLastName());
//    }
    public final class ValoMenuItemButton extends Button {

        private static final String STYLE_SELECTED = "selected";

        private final AppViewType view;

        public ValoMenuItemButton(final AppViewType view) {
            this.view = view;
            setPrimaryStyleName("valo-menu-item");
            setIcon(view.getIcon());
            setCaption(view.getViewName().substring(0, 1).toUpperCase()
                    + view.getViewName().substring(1));
            AppEventBus.register(this);
            addClickListener(new ClickListener() {
                @Override
                public void buttonClick(final ClickEvent event) {
                    UI.getCurrent().getNavigator()
                            .navigateTo(view.getViewName());
                }
            });

        }

        @Subscribe
        public void postViewChange(final PostViewChangeEvent event) {
            removeStyleName(STYLE_SELECTED);
            if (event.getView() == view) {
                addStyleName(STYLE_SELECTED);
            }
        }
    }
}
