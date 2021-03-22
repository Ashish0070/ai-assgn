#include <iostream>

using namespace std;

void multiply(double TP[2][2],
              double initial[1][2],
              double res[1][2])
{
    int i, j, k;
    for (i = 0; i < 2; i++) {
        for (j = 0; j < 2; j++) {
            res[i][j] = 0;
            for (k = 0; k < 2; k++)
                res[i][j] += initial[i][k] * TP[k][j];
        }
    }
}
int main()
{
    int i, j;
    double res[1][2];
    double initial[1][2] ;
    double TP[2][2];
    cout<<"there are 2 states"<<endl;

    for( i=0;i<2;i++)
    {
        for(j=0;j<2;j++)
        {
            cout<<"enter the transition probability:"<<i+1<<"--->"<<j+1<<endl;
            cin>>TP[i][j];

        }
    }
    cout<<"the transition matrix"<<endl;

    for( i=0;i<2;i++)
    {
        for(j=0;j<2;j++)
        {
            cout<<TP[i][j]<<" ";

        }
        cout<<endl;
    }

    cout<<"enter your initial stage"<<endl;
    int n;
    cin>>n;
    for(i=0;i<2;i++)
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
       multiply(initial, TP, res);
       for (int jj=0;jj<2;jj++)
       {
           initial[0][jj]=res[0][jj];
       }

    }

    cout<<"answer is :    "<<res[n-1][f-1];
}
