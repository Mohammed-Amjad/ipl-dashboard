import { React, useState, useEffect } from 'react';
import { FullMatchDetails } from '../components/FullMatchDetails';
import { LessMatchDetails } from '../components/LessMatchDetails';
import { TeamPageHeading } from '../components/TeamPageHeading';
import { useParams } from 'react-router-dom';
import { WinPercentPieChart } from '../components/WinPercentPieChart';
import { Link } from 'react-router-dom';
import './TeamPage.scss'

export const TeamPage = () => {

  const [team, setTeam] = useState({ matches: [] });

  const { teamName } = useParams();

  useEffect(() => {

    const fetchTeamDetails = async () => {
      const response = await fetch(`${process.env.REACT_APP_ROOT_URL}/teams/${teamName}`);
      const data = await response.json();
      setTeam(data);
    }

    fetchTeamDetails();
  }, [teamName]);

  if (!team || !team.teamName) {
    return (
      <div className="TeamPage">
        <nav className="nav-bar">
          <Link to="/" className="home"><h5>Home</h5></Link>
        </nav>
        <div></div>
        <h1>Loading...</h1>
      </div>
    )
  }

  const winPercent = Math.floor(team.matchesWon * 100 / (team.totalMatches - team.matchesDrawn));

  return (
    <div className="TeamPage">
      <nav className="nav-bar">
        <Link to="/" className="home"><h5>Home</h5></Link>
      </nav>
      <div className={`team-name-section`}>
        <TeamPageHeading team={team} />
      </div>
      <div className="win-loss-section">
        <Link to={`/${teamName}/matches/${team.lastPlayedOn.slice(0, 4)}`}>
          <WinPercentPieChart winPercent={winPercent} />
        </Link>
      </div>
      <div className="full-match-section">
        <h3>Latest Matches</h3>
        <FullMatchDetails match={team.matches[0]} teamName={teamName} />
      </div>
      {team.matches.slice(1).map(match => <LessMatchDetails match={match} teamName={teamName} key={match.id} />)}
    </div>
  );
}