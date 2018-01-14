import tkinter as tk
from tkinter import ttk
win = tk.Tk()
win.title('MAH NAME THO')

aLabel = ttk.Label(win, text='label')
aLabel.grid(column=0, row=0)

def clickMe():
    action.configure(text='hello ' + name.get())

ttk.Label(win, text='enter a name:').grid(column=0, row=0)

name = tk.StringVar()
nameEntered = ttk.Entry(win, width=12, textvariable=name)
nameEntered.grid(column=0, row=1)


action = ttk.Button(win, text='click me', command=clickMe)
action.grid(column=1, row=1)


win.mainloop()
