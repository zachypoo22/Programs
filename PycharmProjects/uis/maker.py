option = 1
def op1():
    print('option 1')
def op2():
    print('option 2')
def op3():
    print('option 3')

options = {1: op1, 2: op2, 3: op3}
options[option]()
