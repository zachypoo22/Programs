import sys
from PyQt5.QtWidgets import QApplication, QMainWindow, QAction, qApp

class Notepad(QMainWindow):
    def __init__(self):
        super().__init__()

        bar = self.menuBar()

        file = bar.addMenu('File')
        edit = bar.addMenu('Edit')

        saveaction = QAction('Save', self)
        saveaction.setShortcut('ctrl+S')

        newaction = QAction('New,', self)
        newaction.setShortcut('ctrl+N')

        quitaction = QAction('Quit', self)
        quitaction.setShortcut('ctrl+Q')

        findaction = QAction('find...', self)

        replaceaction = QAction('Replace...', self)


        file.addAction(newaction)
        file.addAction(saveaction)
        file.addAction(quitaction)

        findmenu = edit.addMenu('find...')
        findmenu.addAction(findaction)
        findmenu.addAction(replaceaction)


        quitaction.triggered.connect(self.quit_trigger)
        file.triggered.connect(self.selected)


        self.show()
        


    def quit_trigger(self):
        qApp.quit()

    def selected(self, q):
        print(q.text() + ' selected')

app = QApplication(sys.argv)
w = Notepad()
sys.exit(app.exec_())