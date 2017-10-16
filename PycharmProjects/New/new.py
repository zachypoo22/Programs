import sys
from PyQt5.QtWidgets import QLabel, QCheckBox, QInputDialog, QPushButton, QVBoxLayout, QApplication, QWidget

class Window(QWidget):

    def __init__(self):
        super().__init__()

        self.init_ui()



    def init_ui(self):
        self. lbl = QLabel()
        self.btn = QPushButton('push it')
        self.cb = QCheckBox('likes dogs')

        layout = QVBoxLayout()
        layout.addWidget(self.lbl)
        layout.addWidget(self.cb)
        layout.addWidget(self.btn)

        self.btn.clicked.connect(lambda: self.bclick(self.cb.isChecked(), self.lbl))

        self.setLayout(layout)
        self.show()

    def bclick(self, cb, lbl):
        if cb:
            lbl.setText('Good!')
        else:
            reason, pressed = QInputDialog.getText(self, "Reason", "Why?")
            lbl.setText('terrible reason')


app = QApplication(sys.argv)
window = Window()
sys.exit(app.exec_())