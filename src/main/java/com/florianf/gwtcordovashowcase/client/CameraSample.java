package com.florianf.gwtcordovashowcase.client;

import com.florianf.gwtcordova.client.Cordova;
import com.florianf.gwtcordova.client.plugin.camera.Camera;
import com.florianf.gwtcordova.client.plugin.camera.CameraCallback;
import com.florianf.gwtcordova.client.plugin.camera.CameraOptions;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.vaadin.polymer.paper.widget.PaperButton;

public class CameraSample extends Composite {
    interface CameraSampleUiBinder extends UiBinder<HTMLPanel, CameraSample> {
    }

    @UiField
    PaperButton cameraButton;
    @UiField
    ImageElement myImage;
    @UiField
    SpanElement text;

    private static CameraSampleUiBinder ourUiBinder = GWT.create(CameraSampleUiBinder.class);

    public CameraSample() {
        initWidget(ourUiBinder.createAndBindUi(CameraSample.this));
        cameraButton.addClickHandler(clickEvent -> {
            Camera camera = Cordova.getCamera();
            camera.getPicture(s -> {
                text.setInnerText("success");
                myImage.setSrc(s);
            }, s -> text.setInnerText("error: " + s), new CameraOptions(){});
        });
    }
}
