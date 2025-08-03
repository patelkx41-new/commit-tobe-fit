using System;
using System.Collections.Generic;

public class ChatLogger
{
    private List<(DateTime, string)> messages;

    public ChatLogger()
    {
        messages = new List<(DateTime, string)>();
    }

    public void LogMessage(string message)
    {
        messages.Add((DateTime.Now, message));
    }

    public void DisplayChat()
    {
        Console.WriteLine("Chat History:");
        foreach (var msg in messages)
        {
            Console.WriteLine($"[{msg.Item1}] {msg.Item2}");
        }
    }

    public void SearchMessages(string keyword)
    {
        Console.WriteLine($"Searching for '{keyword}':");
        foreach (var msg in messages)
        {
            if (msg.Item2.Contains(keyword))
            {
                Console.WriteLine($"[{msg.Item1}] {msg.Item2}");
            }
        }
    }
}

// Example usage:
// ChatLogger logger = new ChatLogger();
// logger.LogMessage("Hello!");
// logger.LogMessage("How are you?");
// logger.DisplayChat();
// logger.SearchMessages("Hello");

