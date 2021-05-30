import './App.scss';
import {HashRouter as Router, Route, Switch} from 'react-router-dom';
import {HomePage} from './pages/HomePage'
import {TeamPage} from './pages/TeamPage'
import {MatchPage} from './pages/MatchPage'

function App() {
  return (
    <div className="App">
      <Router>
        <Switch>
          <Route path="/:teamName/matches/:year">
              <MatchPage />
            </Route>
            <Route path="/:teamName">
              <TeamPage />
            </Route>
            <Route path="/">
              <HomePage />
            </Route>
        </Switch>
      </Router>
    </div>
  );
}

export default App;