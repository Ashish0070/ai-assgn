from math import *
import matplotlib.pyplot as plt
import numpy as np

#Gaussian implementation
def gaussian(mu, variance, x):
    return 1.0/sqrt(2.0*pi*variance) * exp(-0.5*(x-mu)**2/variance)

def update(mu1, var1, mu2, var2):
    mu = (var2*mu1 + var1*mu2)/(var1 + var2)
    var = 1/(1/var2 + 1/var1)
    return [mu,var]

def predict(mu1, var1, mu2, var2):
    mu = mu1 + mu2
    var = var1 + var2

    return [mu,var]

def current_gaussian(mu, var):
    print(f'Current Gaussian with mean-{mu} and variance-{var}')
    x_axis = np.arange(-30,30,0.1)
    g=[]
    for x in x_axis:
        g.append(gaussian(mu, var, x))

    plt.plot(x_axis, g)
    plt.show()

def main():
    measurements = [15.0,18.0,19.0,21.0,19.0]
    measurement_var = 4
    motion = [2.0,1.0,2.0,-2.0,1.0]
    motion_var = 2
    mu = 0
    var = 10000

    current_gaussian(mu, var)

    for i in range(len(measurements)):
        mu, var = update(mu, var, measurements[i], measurement_var)
        print(f'Update: [{mu}, {var}]')
        mu, var = predict(mu, var, motion[i], motion_var)
        print(f'Predict: [{mu}, {var}]')

    print(f'\nFinal position: [{mu}, {var}]')

    current_gaussian(mu, var)


if __name__=='__main__':
    main()