import React, { useState, useEffect} from 'react'; 
import PropTypes from 'prop-types';
import { withRouter, Switch, Route, Redirect } from 'react-router-dom';
import { TransitionGroup, CSSTransition } from 'react-transition-group';
import { useInterval } from './Components/Global/useInterval';
import Home from './Components/Home';
import Login from './Components/Login';
import PriceOffer from './Components/PriceOffer'
import {logout} from './Components/Actions/Logout';




// List of routes that uses the page layout
// listed here to Switch between layouts
// depending on the current pathname


const listofPages = [
  '/login',
  '/home',
  '/priceOffer'
];




const PrivateRoute = ({ component: Component, handleNotification, ...rest }) => (
  <Route
    {...rest}
    render={props =>
      localStorage.getItem('access_token') !== 'null' || ' ' ? (
        <Component {...props} handleNotification={handleNotification} />
      ) : (
        <Redirect to='/login' />
      )
    }
  />
);

const Routes = ({ location }) => {
  const currentKey = location.pathname.split('/')[1] || '/';
  const timeout = { enter: 500, exit: 500 };
  const [timer, setTimer] = useState(false);

  let accessToken = localStorage.getItem('access_token');

  useInterval(() => {
    const actualTime = new Date().getTime();
    const expirateDuration = localStorage.getItem('expires_in') * 1;
    // Pop up show 5 minutes before
    // eslint-disable-next-line
    Date.prototype.addMinutes = function (minutes) {
      this.setMinutes(this.getMinutes() + minutes);
      return this;
    };


    accessToken = localStorage.getItem('access_token');
    if (accessToken === null) {
      setTimer(false);
    }

    if (expirateDuration) {
      if (actualTime >= expirateDuration) {
        autoLogout();
      }
    }
  }, timer);

  const autoLogout = () => {
    logout(
      () => {
        setTimer(false);
      },
      () => {
        setTimer(false);
      }
    );
    localStorage.clear();
  };

  useEffect(() => {
    if (accessToken) {
      setTimer(true);
    }
    if (accessToken === null) {
      setTimer(false);
    }
  }, [accessToken, timer]);


  const animationName = 'rag-fadeIn';

  if (listofPages.indexOf(location.pathname) > -1 || !localStorage.getItem('access_token')|| localStorage.getItem('expires_in') <= 0) {
    return (
      // Page Layout component wrapper
        <Switch location={location}>
          <Route path='/login' component={Login} />
          <Route path='*' render={() => <Redirect to='/login' />} />
        </Switch>
    );
  } else {
    return (
      <>
        {/* {redirect ? <Redirect to="/login" /> : ""} */}
          <TransitionGroup>
            <CSSTransition
              key={currentKey}
              timeout={timeout}
              classNames={animationName}
              exit={false}
            >
              <React.Fragment>
                <Switch location={location}>
                  <PrivateRoute
                    exact
                    path='/'
                    component={Home}
                  />
                  <PrivateRoute
                    exact
                    path='/home'
                    component={Home}
                  />
                    <PrivateRoute
                    exact
                    path='/PriceOffer'
                    component={PriceOffer}
                  />
                 {/* <PrivateRoute exact path='/workflows' component={Workflows} /> */}
                </Switch>
              </React.Fragment>
            </CSSTransition>
          </TransitionGroup>
      </>
    );
  }
};

PrivateRoute.propTypes = {
  component: PropTypes.func,
  handleNotification: PropTypes.func
};

export default withRouter(Routes);