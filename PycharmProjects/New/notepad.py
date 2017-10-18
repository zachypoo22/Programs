import sys
import os
from PyQt5.QtWidgets import QApplication, QTextEdit, QWidget,\
    QPushButton, QVBoxLayout, QFileDialog, QHBoxLayout, QMainWindow, QAction, qApp

class Notepad(QWidget):

    def __init__(self):
        super(Notepad, self).__init__()
        self.text = QTextEdit()
        self.clrbtn = QPushButton('Clear')
        self.savebtn = QPushButton('Save')
        self.openbtn = QPushButton('Open')

        self.startUi()

    def startUi(self):
        vlayout = QVBoxLayout()

        hlayout = QHBoxLayout()
        hlayout.addWidget(self.clrbtn)
        hlayout.addWidget(self.savebtn)
        hlayout.addWidget(self.openbtn)

        #menus
        

        vlayout.addWidget(self.text)
        vlayout.addLayout(hlayout)

        self.clrbtn.clicked.connect(self.cls)
        self.savebtn.clicked.connect(self.save)
        self.openbtn.clicked.connect(self.opn)

        self.setLayout(vlayout)
        self.setWindowTitle('Notepadish')

        self.show()

    def cls(self):
        self.text.clear()

    def save(self):
        filename = QFileDialog.getSaveFileName(self, 'Select file pls', os.getenv('HOME'))
        try:
            with open(filename[0], 'w') as f:
                txt = self.text.toPlainText()
                f.write(txt)
        except Exception as e:
            print(e)

    def opn(self):
        try:
            filename = QFileDialog.getOpenFileName(self, 'select file', os.getenv('HOME'))
            with open(filename[0], 'r') as f:
                file_text = f.read()
                self.text.setText(file_text)
        except Exception as e:
            print(e)

class writer(QMainWindow):
    def __init__(self):
        super(writer, self).__init__()

        self.form_widget = Notepad()
        self.setCentralWidget(self.form_widget)

        self.initUi()

    def initUi(self):
        bar = self.menuBar()
        file = bar.addMenu('File')

        newaction = QAction('New', self)
        saveaction = QAction('Save', self)
        openaction = QAction('Open', self)
        quitaction = QAction('Quit', self)

        file.addAction(newaction)
        file.addAction(saveaction)
        file.addAction(openaction)
        file.addAction(quitaction)

        quitaction.triggered.connect(self.quitTrigger)
        newaction.triggered.connect(self.newTrigger)
        saveaction.triggered.connect(self.saveTrigger)
        openaction.triggered.connect(self.openTrigger)
        file.triggered.connect(self.respond)

        self.show()

    def quitTrigger(self):
        qApp.quit()

    def respond(self, q):
        signal = q.text()

        if signal == 'New':
            self.form_widget.cls()
        elif signal == '&Open':
            self.form_widget.opn()
        elif signal == '&Save':
            self.form_widget.save()

    def newTriggger(self):
        pass

    def saveTrigger(self):
        pass

    def openTrigger(self):
        pass

app = QApplication(sys.argv)
writer = writer()
sys.exit(app.exec_())