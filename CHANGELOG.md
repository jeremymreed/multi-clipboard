### v5.2.6
* Added instructions on how to run tests.

### v5.2.5
* Added detailed usage information to README.

### v5.2.4
* Fixed image hover text for screenshots on the README.

### v5.2.3
* Added screenshots to README.
* Fixed formatting of date/time in TimeManager.getFormattedDateWithZone()

### v5.2.2
* Removed Gitlab CI badge for gitlab-ci-update-docker-image branch.

### v5.2.1
* Updated docker image used for Gitlab CI testing.

### v5.2.0
* Acceptance Testing.

### v5.1.3
* Gitlab CI setup.
* License Header cleanup.
* Various refactors.
* Add ROADMAP and CHANGELOG.

### v5.1.2 (2018-06-27)
* Add license header template.
* Bidirectional binding for bufferTextArea.

### v5.1.1 (2018-04-30)
* Fixed a regression.

### v5.1.0 (2018-04-30)
* Add Time Zone string to buffer creation date.
* Adjusted column and app sizing.
* Set default time zone to the local time zone.

### v5.0.0 (2018-04-29)
* Change layout.  (v4 layout sucked)
* Use a TableListView for buffers.

### v4.0.1 (2018-04-04)
* Force app to scroll down when adding a new buffer.

### v4.0.0 (2018-04-04)
* Multiple buffers.
* Change layout.

### v3.0.0 (2018-03-27)
* Add Nuke Clipboard feature.
* Various refactors.
* Fix null pointer issue.
* Improve logging.
* Add JaCoCo Maven Plugin.  (test coverage data)

### v2.4.2 (2018-03-12)
* Refactor. (Make Logger object private final)

### v2.4.1 (2018-03-12)
* Move default log file location.

### v2.4.0 (2018-03-10)
* Layout changes.
* Logging config changes.
* Add About and License alerts. (Help -> About, Help -> License)
* Implement File -> Close menu item.
* Styling changes.

### v2.3.1 (2018-03-09)
* Fix null pointer errors.

### v2.3.0 (2018-03-09)
* Add Clear Buffer button.
* Add Buffer Wrap Text radio button.
* Clean up status messages.

### v2.2.0 (2018-03-09)
* Convert to Maven project.

### v2.1.1 (2018-03-08)
* Add exception handling.  (Getting NPEs)
* Add Logging.
* Add integration tests for the clipboard interface.
* Add a method to make the system clipboard empty.

### v2.1.0 (2018-02-25)
* Add a method to determine if the system clipboard is empty.
* Handle empty system clipboard properly.

### v2.0.0 (2018-02-23)
* Add Clipboard Monitor Threads.
* Buffer is no longer editable, now holds system clipboard value.
* Cache system clipboard's old value, and only change clipboard text area when the system clipboard's value changes.
* Add ThreadManager. (Start and Stop/Shutdown threads)
* Add buffer Text Area.
* Add Clear Clipboard button.
* Add Clipboard Wrap Text radio button.
* Set a title for the application's window.

### v1.0.1 (2018-02-15)
* Fix FXML layout file to avoid exception.

### v1.0.0 (2018-02-11)
* Initial release.
* Create a basic UI.
* Can read in from clipboard, and write to clipboard.
* Editable buffer.
