package com.valentingc.javafxclient.controller.internal;

import com.valentingc.javafxclient.dto.LoginDto;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Async task that can be used for the login process (non-blocking GUI).
 *
 * @author Valentin Goronjic
 */
public class UserLoginTask extends AsyncTask<Boolean> {
    private static final Logger LOG = LogManager.getLogger(UserLoginTask.class);
    private final LoginDto loginUser;
    private final AnchorPane pane;

    /**
     * Sets all required values for the UserLoginTask.
     *
     * @param loginUser      LoginDto
     * @param pane           AnchorPane
     */
    public UserLoginTask(LoginDto loginUser, AnchorPane pane) {
        super(pane);
        this.loginUser = loginUser;
        this.pane = pane;
    }

    @Override
    protected Boolean call() throws Exception {
        super.call();
        LOG.debug("Perform user login");
        return true; // TODO implement auth
    }

    @Override
    protected void failed() {
        super.failed();

        AlertHelper.showAlert(
            Alert.AlertType.ERROR,
            this.pane.getScene().getWindow(),
            "Login failed",
            "Could not check credentials!"
        );

    }
}
