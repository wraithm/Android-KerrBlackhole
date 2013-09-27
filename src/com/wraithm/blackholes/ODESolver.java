package com.wraithm.blackholes;

public class ODESolver {
    public final double M = 1.0; //Mass
    public final double a = 3.0; //Angm/mass
    private final int MAXVAR = 10;
    
    public ODESolver() {}
    
    private void Derivs(double t, double XT[], double XF[]) {
        XF[1] = XT[5];
        XF[2] = XT[6];
        XF[3] = XT[7];
        XF[4] = XT[8];
        
        XF[5] = (M*(XT[6]*((a*a - 2*Math.pow(XT[2], 2) + a*a*Math.cos(2*XT[3]))*(Math.pow(a, 4) + 2*Math.pow(XT[2], 4) + 3*a*a*XT[2]*(-2*M+XT[2]) + a*a*(a*a + XT[2]*(6*M + XT[2]))*Math.cos(2*XT[3]))*XT[5] + 8*a*(Math.pow(XT[2], 4)*(a*a + 3*Math.pow(XT[2], 2)) + 4*a*a*Math.pow(XT[2], 4)*Math.pow(Math.cos(XT[3]), 2) + (- Math.pow(a, 6) + a*a*Math.pow(a*XT[2], 2))*Math.pow(Math.cos(XT[3]), 4))*Math.pow(Math.sin(XT[3]), 2)*XT[8]) + a*a*XT[2]*XT[7]*(2*(Math.pow(a, 4) + 2*Math.pow(XT[2], 3)*(-8*M + XT[2]) + a*a*XT[2]*(-14*M + 3*XT[2]) + a*a*(a*a + XT[2]*(-2*M + XT[2]))*Math.cos(2*XT[3]))*Math.sin(2*XT[3])*XT[5] - 8*a*(a*a + XT[2]*(-2*M + XT[2]))*Math.cos(XT[3])*(a*a + 2*Math.pow(XT[2], 2) + a*a*Math.cos(2*XT[3]))*Math.pow(Math.sin(XT[3]), 3)*XT[8])))/(2*(Math.pow(XT[2], 2) + Math.pow(a*Math.cos(XT[3]),2))*(2*Math.pow(a*XT[2], 2)*(a*a + 2*M*M - 2*M*XT[2] + Math.pow(XT[2], 2))*Math.pow(Math.cos(XT[3]), 2) + Math.pow(a, 4)*(a*a + XT[2]*(-2*M + XT[2]))*Math.pow(Math.cos(XT[3]), 4) + Math.pow(XT[2], 2)*(Math.pow(XT[2], 3) *(-2*M + XT[2]) + a*a*(4*M*M + Math.pow(XT[2], 2)) - 8*a*a*M*M*Math.cos(2*XT[3]))));
        XF[6] = (-(Math.pow(Math.pow(XT[2], 2) + a*a*Math.pow(Math.cos(XT[3]), 2), 2)*(XT[2]*(a*a - M*XT[2]) + a*a*(M - XT[2])*Math.pow(Math.cos(XT[3]), 2))*Math.pow(XT[6], 2))/(a*a + XT[2]*(-2*M + XT[2])) + (a*a + XT[2]*(-2*M + XT[2]))*(-M*Math.pow(XT[2], 2) + a*a*M*Math.pow(Math.cos(XT[3]), 2))*Math.pow(XT[5], 2) + 2*a*a*Math.cos(XT[3])*Math.pow(Math.pow(XT[2], 2) + a*a*Math.pow(Math.cos(XT[3]), 2), 2)*Math.sin(XT[3])*XT[6]*XT[7] + XT[2]*(a*a + XT[2]*(-2*M + XT[2]))*Math.pow(Math.pow(XT[2], 2) + a*a*Math.pow(Math.cos(XT[3]), 2), 2)*Math.pow(XT[7], 2) - 2*a*M*(a*a + XT[2]*(-2*M + XT[2]))*(a*a - 2*Math.pow(XT[2], 2) + a*a*Math.cos(2*XT[3]))*Math.pow(Math.sin(XT[3]), 2)*XT[5]*XT[8] - (a*a + XT[2]*(-2*M + XT[2]))*(a*a*M*Math.pow(XT[2], 2) - Math.pow(XT[2], 5) - a*a*(a*a*M + Math.pow(XT[2], 2)*(M + 2*XT[2]))*Math.pow(Math.cos(XT[3]), 2) + Math.pow(a, 4)*(M - XT[2])*Math.pow(Math.cos(XT[3]), 4))*Math.pow(Math.sin(XT[3])*XT[8], 2))/Math.pow(Math.pow(XT[2], 2) + a*a*Math.pow(Math.cos(XT[3]), 2), 3);
        XF[7] = (-(a*a*Math.cos(XT[3])*Math.pow(Math.pow(XT[2], 2) + Math.pow(a*Math.cos(XT[3]), 2), 2)*Math.sin(XT[3])*Math.pow(XT[6], 2))/(a*a + XT[2]*(-2*M + XT[2])) + a*a*M*XT[2]*Math.sin(2*XT[3])*Math.pow(XT[5], 2) - 2*XT[2]*Math.pow(Math.pow(XT[2], 2) + Math.pow(a*Math.cos(XT[3]), 2), 2)*XT[6]*XT[7] + a*a*Math.cos(XT[3])*Math.pow(Math.pow(XT[2], 2) + Math.pow(a*Math.cos(XT[3]), 2), 2)*Math.sin(XT[3])*Math.pow(XT[7], 2) - 4*a*M*XT[2]*(a*a + Math.pow(XT[2], 2))*Math.sin(2*XT[3])*XT[5]*XT[8] + Math.cos(XT[3])*Math.pow(Math.pow(XT[2], 2) + Math.pow(a*Math.cos(XT[3]), 2), 2)*Math.sin(XT[3])*(a*a - 2*M*XT[2] + Math.pow(XT[2], 2) + (2*M*XT[2]*(a*a + Math.pow(XT[2], 2)))/(Math.pow(XT[2], 2) + Math.pow(a*Math.cos(XT[3]), 2)) + (2*a*a*M*XT[2]*(a*a + Math.pow(XT[2], 2))*Math.pow(Math.sin(XT[3]), 2))/Math.pow(Math.pow(XT[2], 2) + Math.pow(a*Math.cos(XT[3]), 2), 2))*Math.pow(XT[8], 2))/Math.pow(Math.pow(XT[2], 2) + Math.pow(a*Math.cos(XT[3]), 2), 3);
        XF[8] = (2*XT[6]*(2*a*M*(-Math.pow(XT[2], 4) + Math.pow(a*Math.cos(XT[3]), 4))*XT[5] + (-Math.pow(a, 4)*(a*a*M + XT[2]*(2*M*M - 2*M*XT[2] + 3*Math.pow(XT[2], 2)))*Math.pow(Math.cos(XT[3]), 4) + Math.pow(a, 6)*(M - XT[2])*Math.pow(Math.cos(XT[3]), 6) + Math.pow(XT[2], 3)*((2*M - XT[2])*Math.pow(XT[2], 3) + a*a*M*(2*M + XT[2]) - 4*a*a*M*M*Math.cos(2*XT[3])) + XT[2]*Math.pow(a*Math.cos(XT[3]), 2)*(-2*a*a*M*M + Math.pow(XT[2], 2)*(2*M*M + 3*M*XT[2] - 3*Math.pow(XT[2], 2)) + 4*a*a*M*M*Math.cos(2*XT[3])))*XT[8]) - 0.5*XT[7]*(-8*a*M*XT[2]*(a*a + XT[2]*(-2*M + XT[2]))*(a*a + 2*Math.pow(XT[2], 2) + a*a*Math.cos(2*XT[3]))*Math.cos(XT[3])*XT[5]/Math.sin(XT[3]) + (4*(Math.pow(XT[2], 2) + Math.pow(a*Math.cos(XT[3]), 2))*(-(2*M - XT[2])*Math.pow(XT[2], 2)*(Math.pow(XT[2], 3) + a*a*(2*M + XT[2])) + 2*a*a*Math.pow(XT[2], 2)*(a*a + 2*M*M - 2*M*M - 2*M*XT[2] + Math.pow(XT[2], 2))*Math.pow(Math.cos(XT[3]), 2) + Math.pow(a, 4)*(a*a + XT[2]*(-2*M + XT[2]))*Math.pow(Math.cos(XT[3]), 4))*Math.cos(XT[3])/Math.sin(XT[3]) + 2*a*a*M*XT[2]*(a*a + Math.pow(XT[2], 2))*(a*a + 2*XT[2]*(6*M + XT[2]) + a*a*Math.cos(2*XT[3]))*Math.sin(2*XT[3]))*XT[8]))/((Math.pow(XT[2], 2) + Math.pow(a*Math.cos(XT[3]), 2))*(2*a*a*Math.pow(XT[2], 2)*(a*a + 2*M*M - 2*M*XT[2] + Math.pow(XT[2], 2))*Math.pow(Math.cos(XT[3]), 2) + Math.pow(a, 4)*(a*a + XT[2]*(-2*M + XT[2]))*Math.pow(Math.cos(XT[3]), 4) + Math.pow(XT[2], 2)*(Math.pow(XT[2], 3)*(-2*M + XT[2]) + a*a*(4*M*M + Math.pow(XT[2], 2)) - 8*a*a*M*M*Math.cos(2*XT[3]))));
    }
    
    private void rk4(double y[], double dydt[], int n, double t, double h, double yout[]) {
        int i;
        double th, hh, h6;
        double dym[] = new double[MAXVAR], dyt[] = new double[MAXVAR], yt[] = new double[MAXVAR];
        
        hh = 0.5*h;
        h6 = h/6;
        th = t + hh;
        
        for(i = 1; i <= n; i++) yt[i] = y[i] + hh * dydt[i];
        
        Derivs(th, yt, dyt);
        
        for(i = 1; i <= n; i++) yt[i] = y[i] + hh * dyt[i];
        
        Derivs(th, yt, dym);
        
        for(i = 1; i <= n; i++) {
            yt[i] = y[i] + h * dym[i];
            dym[i] = dyt[i] + dym[i];
        }
        
        Derivs(t + h, yt, dyt);
        
        for(i = 1; i <= n; i++) yout[i] = y[i] + h6*(dydt[i] + dyt[i] + 2.0*dym[i]);
    }

    private void rkqc(
            double y[], double dydt[], int n, double t, double htry,
            double eps, double yscal[], double hdid, double hnext) {
        final double pgrow = -0.20,
                     pshrnk = -0.25,
                     fcor = 0.06666666,
                     un = 1.0,
                     safety = 0.9,
                     errcon = 6e-4,
                     tiny = 1e-20;
        
        int i;
        double tsav, hh, h, temp, errmax;
        double dysav[] = new double[MAXVAR], ysav[] = new double[MAXVAR], ytemp[] = new double[MAXVAR];
        
        tsav = t; //maybe 1?
        
        for(i = 1; i <= n; i++) {
            ysav[i] = y[i];
            dysav[i] = dydt[i];
        }
        
        h = htry;
        
        do {
            hh = 0.5*h;
            rk4(ysav, dysav, n, tsav, hh, ytemp);
            t = tsav + hh;
            Derivs(t, ytemp, dydt);
            rk4(ytemp, dydt, n, t, hh, y);
            t = tsav + h;
            
            //if(t[0] == tsav)
                //Increment too small of indep var
            
            rk4(ysav, dysav, n, tsav, h, ytemp);
            errmax = 0;
            temp = 0;
            
            for(i = 1; i <= n; i++) {
                ytemp[i] = y[i] - ytemp[i];
                if(yscal[i] > tiny) temp = Math.abs(ytemp[i]/yscal[i]);
                if(errmax < temp) errmax = temp;
            }
            
            errmax = errmax/eps;
            
            h = safety * h * Math.exp(pshrnk*Math.log(errmax));
        } while(errmax > un);
        
        hdid = h;
        if(errmax > errcon)
            hnext = safety*h*Math.exp(pgrow*Math.log(errmax));
        else
            hnext = 4.0*h;
        
        for(i = 1; i <= n; i++) y[i] += ytemp[i]*fcor;
    }
    
    public void odeint(
            double ystart[], int nvar, double t1, double t2, double eps,
            double h1, double hmin, int nok, int nbad) {
        final int maxstp = 100; //was 10000
        
        int nstp, i;
        double t, hnext = 0, hdid = 0, h;
        double yscal[] = new double[MAXVAR], y[] = new double[MAXVAR], dydt[] = new double[MAXVAR];
        
        t = t1;
        
        if(t2 > t1)
            h = Math.abs(h1);
        else
            h = -Math.abs(h1);
        
        nok = 0;
        nbad = 0;
        
        for(i = 1; i <= nvar; i++) y[i] = ystart[i];
        
        for(nstp = 1; nstp <= maxstp; nstp++) {
            Derivs(t, y, dydt);
            for(i = 1; i <= nvar; i++) yscal[i] = Math.abs(y[i]) + Math.abs(dydt[i]*h);
            if(((t + h - t2)*(t + h - t1)) > 0.0) h = t2 -t;
            rkqc(y, dydt, nvar, t, h, eps, yscal, hdid, hnext);
            if(hdid == h)
                nok = nok + 1;
            else
                nbad = nbad + 1;
            
            if(((t - t2)*(t2 - t1)) >= 0.0) {
                for(i = 1; i <= nvar; i++) ystart[i] = y[i];
                break;
            }
            
            h = hnext;
        }
        //TOO MANY TIME STEPS!
        
        h1 = h;
    }
}
