import sys
from PyQt5.Qt import QPushButton, QApplication, QVBoxLayout, QHBoxLayout, QLineEdit, QTextEdit, QWidget, QLabel
import re

class Window(QWidget):

    def __init__(self):
        super().__init__()
        self.setupUi()
        self.main()

    def setupUi(self):


        btnHbox = QHBoxLayout()
        self.change = QPushButton('Change Password')
        self.dump = QPushButton('Dump Data')
        btnHbox.addWidget(self.change)
        btnHbox.addWidget(self.dump)

        oldHbox = QHBoxLayout()
        self.oldLabel = QLabel('Old Password')
        self.oldPWText = QLineEdit()
        oldHbox.addWidget(self.oldLabel)
        oldHbox.addWidget(self.oldPWText)

        newHbox = QHBoxLayout()
        self.newLabel = QLabel('New Password')
        self.newPWText = QLineEdit()
        newHbox.addWidget(self.newLabel)
        newHbox.addWidget(self.newPWText)

        userHbox = QHBoxLayout()
        self.userLabel = QLabel('Username')
        self.usernameText = QLineEdit()
        userHbox.addWidget(self.userLabel)
        userHbox.addWidget(self.usernameText)

        leftVbox = QVBoxLayout()
        leftVbox.addLayout(userHbox)
        leftVbox.addLayout(oldHbox)
        leftVbox.addLayout(newHbox)
        leftVbox.addLayout(btnHbox)

        mainHbox = QHBoxLayout()
        self.textArea = QTextEdit()
        mainHbox.addLayout(leftVbox)
        mainHbox.addWidget(self.textArea)


        self.setLayout(mainHbox)
        self.show()

    def changed(self):
        username = self.usernameText.text()
        oldPassword = self.oldPWText.text()
        newPassword = self.newPWText.text()

        if not self.isValidLogin(username):
            self.textArea.setText('Validation: Login format incorrect')
            return

        if username not in self.logins:
            self.textArea.setText('Validation: No such user')
            return

        if oldPassword != self.logins[username]:
            self.textArea.setText('Validation: Current Password Incorrect')
            return

        if len(newPassword) < 8:
            self.textArea.setText('Validation: OK \nNew Password: Too Short')
            return

        if newPassword == oldPassword:
            self.textArea.setText('Validation: OK \nNew Password: Cannot be same')
            return

        if not self.isStrongPassword(newPassword):
            self.textArea.setText('Validation: OK \nNew Password: Too Weak')
            return

        self.logins[username] = newPassword


    def dumped(self):
        for i in self.logins:
            print(i, self.logins.get(i))

    def isValidLogin(self, s):
        m = re.search("[a-zA-Z]{2}[0-9]{6}@wcupa.edu", s)
        if m is None:
            return False
        else:
            return True

    def isStrongPassword(self, s):
        strength = 0
        hasDigit = False
        hasUpper = False
        hasSymbol = False
        hasLower = False


        for c in s:
            if c.isdigit():
                hasDigit = True

            if c.isupper():
                hasUpper = True

            if c.islower():
                hasLower = True

            if not c.isalnum():
                hasSymbol = True

        if hasSymbol:
            strength += 1
        if hasDigit:
            strength += 1
        if hasUpper:
            strength += 1
        if hasLower:
            strength += 1

        if strength >= 3:
            return True
        else:
            return False


    def main(self):
        self.logins = {"ab111111@wcupa.edu": "a2Foofoo",
                       "cd222222@wcupa.edu": "b.Barbar",
                       "ef333333@wcupa.edu": "123456.x"
                       }
        self.change.clicked.connect(self.changed)
        self.dump.clicked.connect(self.dumped)


app = QApplication(sys.argv)
window = Window()
sys.exit(app.exec_())