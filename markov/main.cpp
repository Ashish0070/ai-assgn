#include <iostream>

using namespace std;

void multiply(double TP[100][100],
              double initial[1][100],
              double res[1][100], 
              int n)
{
    int i, j, k;
    for (i = 0; i < n; i++) {
        for (j = 0; j < n; j++) {
            res[i][j] = 0;
            for (k = 0; k < n; k++)
                res[i][j] += initial[i][k] * TP[k][j];
        }
    }
}
int main()
{
    int i, j;
    int noOfStates;

    cout<<"Enter the number of states: "<<endl;
    cin >> noOfStates;

    double res[1][100];
    double initial[1][100] ;
    double TP[100][100];

    for( i=0;i<noOfStates;i++)
    {
        for(j=0;j<noOfStates;j++)
        {
            cout<<"enter the transition probability:"<<i+1<<"--->"<<j+1<<endl;
            cin>>TP[i][j];

        }
    }
    cout<<"the transition matrix"<<endl;

    for( i=0;i<noOfStates;i++)
    {
        for(j=0;j<noOfStates;j++)
        {
            cout<<TP[i][j]<<" ";

        }
        cout<<endl;
    }

    cout<<"enter your initial stage"<<endl;
    int n;
    cin>>n;
    for(i=0;i<noOfStates;i++)
    {
       initial[0][i]=0;
    }
    initial[0][n-1]=1;
    cout<<"enter your time unit"<<endl;
    int t;
    cin>>t;
    cout<<"enter the final state"<<endl;
    int f;
    cin>>f;


    for (int ii=0;ii<=t;ii++)
    {
       multiply(initial, TP, res, noOfStates);
       for (int jj=0 ; jj<noOfStates ; jj++)
       {
           initial[0][jj]=res[0][jj];
       }

    }

    cout<<"answer is :    "<<res[n-1][f-1]<<endl;
}
