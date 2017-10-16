import sys
from PyQt5 import QtGui
from PyQt5.QtWidgets import QApplication, QDialog, QMainWindow
from ui_imagedialog import ImageDialog
import  re


class ImageDialog(QMainWindow, ImageDialog):
    logins = {'zach': '123abc', 'John': '456def', 'guy': '789zqr'}

    def __init__(self):
        super(ImageDialog, self).__init__()

        # Set up the user interface from Designer.
        self.setupUi(self)

        #actually do things
        self.dumpButton.clicked.connect(self.dump)
        self.changeButton.clicked.connect(self.change)

    def dump(self):
        for i in self.logins:
            print(i, self.logins.get(i))

    def change(self):
        if not self.userNameText.toPlainText() in self.logins:
            self.textArea.setText("wrong username")
            return
        if not self.newPasswordText.toPlainText().len() >= 8:
            self.textArea.setText("password too short")
            return
        if not self.oldPasswordText.toPlainText() == self.logins.get(self.userNameText.toPlainText()):
            self.textArea.setText("wrong password")
            return
        if self.oldPasswordText.toPlainText() == self.newPasswordText.toPlainText():
            self.textArea.setText("cant be same password")
            return
        if not self.passPower(self.newPasswordText.toPlainText()):
            self.textArea.setText("too weak")
            return
        self.logins[self.userNameText.toPlainText()] = self.newPasswordText.toPlainText()

    def passPower(self, s):
        str = 0
        hasDigit = False
        hasUpper = False
        hasSymbol = False
        hasLower = False

        for c in s:
            if c.isdigit():
                hasDigit = True
            if c.isuppercase:
                hasUpper = True
            if c.islowercase:
                hasLower = True
            if not c.isalnum():
                hasSymbol = True

        if hasSymbol:
            str += 1
        if hasDigit:
            str += 1
        if hasUpper:
            str += 1
        if hasLower:
            str += 1

        return str >= 3

    def usernamePower(self, s):
        m = re.search("[a-zA-Z]{2}[0-9]{6}@wcupa.edu", s)
        if m is None:
            return False
        else:
            return True

def main():
    app = QApplication(sys.argv)
    window = ImageDialog()
    window.show()
    sys.exit(app.exec_())


if __name__ == '__main__':
    main()
