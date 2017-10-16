import sys
from PyQt5 import QtWidgets


class Window(QtWidgets.QWidget):

    def __init__(self):
        super().__init__()

        self.init_ui()

    def init_ui(self):
        self.b = QtWidgets.QPushButton("push me")
        self.l = QtWidgets.QLabel("I have not been clicked")

        hbox = QtWidgets.QHBoxLayout()
        hbox.addStretch()
        hbox.addWidget(self.l)
        hbox.addStretch()

        a = QtWidgets.QInputDialog.getDouble(self, "get this double", "enter:")
        print(a) # (input, boolean)

        vbox = QtWidgets.QVBoxLayout()
        vbox.addWidget(self.b)
        vbox.addLayout(hbox)

        self.setLayout(vbox)
        self.setWindowTitle('my ui')

        self.b.clicked.connect(self.btnclick)

        self.show()

    def btnclick(self):
        self.l.setText('clicked asf')


app = QtWidgets.QApplication(sys.argv)
awindow = Window()
sys.exit(app.exec_())