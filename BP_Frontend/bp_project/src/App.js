import React, {Component} from 'react';
import './App.css';
import { BrowserRouter } from 'react-router-dom';
import $ from 'jquery';

// App Routes
import Routes from './Routes';

//import { globalVariables } from '../src/styles/app/common/variablesExport.js';


// Disable warning "Synchronous XMLHttpRequest on the main thread is deprecated.."
$.ajaxPrefilter(o => (o.async = true));

class App extends Component {
  componentDidMount() {
    document.title = 'FEI Bakalárska práca';
  }

  componentWillUnmount() {
    // clearInterval(this.interval);
  }

  render() {
    // specify base href from env varible 'WP_BASE_HREF'
    // use only if application isn't served from the root
    // for development it is forced to root only
    /* global WP_BASE_HREF */
    const basename = process.env.NODE_ENV === 'development' ? '/' : WP_BASE_HREF || '/';

    return (
      <BrowserRouter basename={basename}>
        <Routes props={this.props} />
      </BrowserRouter>
    );
  }
}

export default (App);