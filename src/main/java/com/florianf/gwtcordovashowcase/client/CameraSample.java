package com.florianf.gwtcordovashowcase.client;

import com.florianf.gwtcordova.client.plugin.camera.Camera;
import com.florianf.gwtcordova.client.plugin.camera.CameraConstants;
import com.florianf.gwtcordova.client.plugin.camera.CameraOptions;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

public class CameraSample extends Composite {
    interface CameraSampleUiBinder extends UiBinder<HTMLPanel, CameraSample> {
    }

    @UiField
    Button cameraButton;
    @UiField
    ImageElement image;
    @UiField
    SpanElement text;

    private static CameraSampleUiBinder ourUiBinder = GWT.create(CameraSampleUiBinder.class);

    public CameraSample() {
        initWidget(ourUiBinder.createAndBindUi(CameraSample.this));
        cameraButton.addClickHandler(clickEvent -> {
            CameraOptions cameraOptions = new CameraOptions();
            cameraOptions.setDestinationType(CameraConstants.DestinationType.FILE_URI);
            Camera.getPicture(s -> {
                text.setInnerText("success");
                image.setSrc(s);
                image.setAttribute("style", "max-width: 50%; max-height: 50%");

                return null;

            }, s -> { text.setInnerText("error: " + s); return null; }, cameraOptions);
        });
    }
}
